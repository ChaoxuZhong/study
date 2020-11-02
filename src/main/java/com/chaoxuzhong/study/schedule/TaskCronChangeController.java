package com.chaoxuzhong.study.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dynamic-scheduled")
public class TaskCronChangeController {
    @Autowired
    TaskCronChange taskCronChange;

    @RequestMapping(value = "/update-cron")
    public String updateDynamicScheduledTask(@RequestParam("cron") String cron) {

        taskCronChange.setCron(cron);

        return "success";
    }
}
