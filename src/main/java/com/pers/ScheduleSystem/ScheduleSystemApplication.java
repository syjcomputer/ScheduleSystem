package com.pers.ScheduleSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ScheduleSystemApplication {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {
        SpringApplication.run(ScheduleSystemApplication.class, args);
    }

}
