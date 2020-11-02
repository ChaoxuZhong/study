package com.chaoxuzhong.study.local.localDateTime;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 定时更新时间
 */
@Component
@EnableScheduling
public class ScheduledRefreshDate {

    static List<String> holidays = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(ScheduledRefreshDate.class);

    /**
     * 模拟数据库设置节假日
     * @return
     */
    public static void setHolidays(List<String> holidaysList){
        logger.info("setHolidays = {}" + JSON.toJSONString(holidaysList));
        holidays = holidaysList;
    }

    /**
     * 模拟数据库获取节假日
     * @return
     */
    private List<String> getHolidays(){
        return holidays;
    }

//    @Scheduled(cron="0 0 0 1/1 * ?")
    @Scheduled(cron="0/20 * * * * ?")
    public void refreshHoliday() {
        CatchWorkingDayUtil.getInstance().clearCache();
        logger.info("refresh holiday time = " + LocalDateTime.now().toString());
        LocalDateTimeUtil.refreshHolidays(getHolidays());
    }

}
