package com.chaoxuzhong.study.local.localDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/localDate")
public class LocalDateTestController {

    private Logger logger = LoggerFactory.getLogger(LocalDateTestController.class);

    /**
     * 通过请求模拟数据库的变更
     *
     * @param holidaysStr
     * @return
     */
    @PostMapping("/mockRefreshDB")
    public String refresh(@RequestBody List<String> holidaysStr) {
        ScheduledRefreshDate.setHolidays(holidaysStr);
        return "刷新数据库成功";
    }

    /**
     * 获取工作日
     * @return
     */
    @GetMapping("/getWorkingDays")
    public long getWorkingDays(){
        try {
            return LocalDateTimeUtil.calWorkingDays(LocalDateTimeUtil.buildLocalDate("2020-09-01")
                    , LocalDate.now());
        } catch (Exception e) {
            logger.error("getWorkingDays ERROR == {}", e);
        }
        return 0;
    }

}
