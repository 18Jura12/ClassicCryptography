package com.java.crypto.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfiguration {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        log.debug("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        int maxThreadNumber = new SystemInfo().getHardware().getProcessor().getLogicalProcessorCount();

        executor.setCorePoolSize(maxThreadNumber);
        executor.setMaxPoolSize(maxThreadNumber);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("HillThread-");
        executor.initialize();
        return executor;
    }

}
