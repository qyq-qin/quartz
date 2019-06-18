package com.qf.springboot_quartz.quartz;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class ComplexJob implements SchedulingConfigurer {

    @Mapper
    public interface CronMapper{
        @Select("select cron from cron limit 1")
        String getCon();
    }

    @Autowired
    @SuppressWarnings("all")
    CronMapper cronMapper;

    //任务，触发器 lambada
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        scheduledTaskRegistrar.addTriggerTask(
                () -> System.out.println(LocalDateTime.now()),
                triggerContext -> {
                    String con = cronMapper.getCon();
                    return new CronTrigger(con).nextExecutionTime(triggerContext);
                }
        );

    }
}
