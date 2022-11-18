package com.animals.WebService.scheduler;

import com.animals.WebService.job.StageJob;
import org.quartz.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@EnableScheduling
public class JobScheduler {
    @Bean()
    public Scheduler scheduler(SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        return scheduler;
    }

    @Bean
    public CommandLineRunner run(Scheduler scheduler) {
        return (String[] args) -> {

            JobDetail stageJob = JobBuilder.newJob(StageJob.class)
                    .build();


            CronTrigger triggerStageJob = TriggerBuilder.newTrigger()
                    .withIdentity("trigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();



            scheduler.scheduleJob(stageJob, triggerStageJob);
        };
    }
}
