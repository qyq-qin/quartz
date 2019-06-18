package com.qf.springboot_quartz.quartz;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling //开启任务调度
public class SimpleJob {

    @Scheduled(cron = "0/1 * * * * ?")
    public void run(){

        //任务
        System.err.println(LocalDateTime.now());
    }
}
