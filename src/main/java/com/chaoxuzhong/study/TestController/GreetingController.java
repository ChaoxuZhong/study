package com.chaoxuzhong.study.TestController;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicLong;

import com.chaoxuzhong.study.Hello.Greeting;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableConfigurationProperties
@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	/**
	 * 自定义消息转换器
	 * @return
	 */
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return converter;
	}

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="世界") String name) {
		return new Greeting(counter.incrementAndGet(),
							String.format(template, name));
	}

}
