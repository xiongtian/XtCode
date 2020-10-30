package com.example.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedduledService {


    /*
     * on the second(秒), minute(分), hour(时), day of month(日), month(月), and day of week(周几).
     * <p>For example, {@code "0 * * * * MON-FRI"} means once per minute on weekdays
     * */
    // @Scheduled(cron = "0 * * * * MON-FRI")
    //@Scheduled(cron = "1,2,3,4,5 * * * * MON-FRI")  枚举
    //@Scheduled(cron = "1-4 * * * * MON-FRI")        区间
    //@Scheduled(cron = "1/4 * * * * MON-FRI")        步长（0秒开始，每四秒执行一次）
    public void hello() {

        System.out.println("hello...");
    }
}
