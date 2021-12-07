package ru.sibdigital.jopsd.schedule.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@ComponentScan(basePackages = "ru.sibdigital.jopsd.schedule", basePackageClasses = { SchedulerBrbo.class })
public class SchedulerBrboConfig {
    @Value("${expressionBrbo}") String expression;
    @Value("${expressionCheckRegTargetSystemUserBrbo}") String expressionCheckRegTargetSystemUser;
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
    @Bean
    public CronTrigger cronTriggerCheckRegTargetSystemUser() {
        return new CronTrigger(expressionCheckRegTargetSystemUser);
    }

}