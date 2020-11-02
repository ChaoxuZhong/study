package com.chaoxuzhong.study.local.localDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LocalDateTimeUtil {

    private static Logger logger = LoggerFactory.getLogger(LocalDateTimeUtil.class);


    private static CatchWorkingDayUtil util = CatchWorkingDayUtil.getInstance();

    public static List<String> holidays = new ArrayList<>();

    public static final DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ISO_DATE;

    /**
     * 创建LocalDate
     *
     * @param time 年-月-日
     * @return
     */
    public static LocalDate buildLocalDate(String time) {
        return LocalDate.parse(time, ISO_DATE_FORMATTER);
    }

    /**
     * 刷新holidays
     *
     * @param newHolidays
     */
    public static void refreshHolidays(List<String> newHolidays) {
        holidays.clear();
        holidays.addAll(newHolidays);
    }

    /**
     * 获取两个时间（LocalDate)的天数差
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static long getDaysBetweenLocalDate(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 获取到今天的天数差
     *
     * @param startDate
     * @return
     */
    public static long getDaysFromNow(LocalDate startDate) {
        return getDaysBetweenLocalDate(startDate, LocalDate.now());
    }

    /**
     * 计算节假日
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long countHolidays(LocalDate startDate, LocalDate endDate) {
        long holidayCount = 0;
        long days = getDaysBetweenLocalDate(startDate, endDate);
        if (days <= 0) {
            throw new IllegalArgumentException("startDate should be earlier than endDate");
        }
        // 介于开始、结束时间之间的每一天
        LocalDate eachDateInDates = startDate;
        // 计算节假日
        for (int i = 0; i < days; i++) {
            if (holidays.contains(eachDateInDates.toString())) {
                holidayCount++;
            }
            eachDateInDates = eachDateInDates.plusDays(1);
        }
        return holidayCount;
    }

    /**
     * 计算到今天的节假日
     *
     * @param startDate
     * @return
     */
    public static long countHolidaysFromNow(LocalDate startDate) {
        return countHolidays(startDate, LocalDate.now());
    }

    /**
     * 计算工作日
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long calWorkingDays(LocalDate startDate, LocalDate endDate) {
        Long workingDays;
        String cacheDateKey = startDate.toString() + "|" + endDate.toString();
        logger.info("calWorkingDays cacheDateKey ={}", cacheDateKey);
        if (util.isContain(cacheDateKey)) {
            workingDays = (Long) util.get(cacheDateKey);
            logger.info("calWorkingDays get cache workingDays = {}", workingDays);
        } else {
            workingDays = getDaysBetweenLocalDate(startDate, endDate) - countHolidays(startDate, endDate);
            logger.info("calWorkingDays put cache workingDays = {}", workingDays);
            util.put(cacheDateKey, workingDays);
        }
        return workingDays;
    }


}
