package ru.sibdigital.jopsd.schedule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@ComponentScan(basePackages = "ru.sibdigital.jopsd.schedule", basePackageClasses = { SchedulerBrbo.class })
public class SchedulerBrboConfig {
    @Value("${expression}") String expression;
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");

        return threadPoolTaskScheduler;
    }

    @Bean
    public CronTrigger cronTrigger() {
        return new CronTrigger(expression);
    }

}