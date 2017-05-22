package com.sibat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartPoliceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartPoliceApplication.class, args);
    }
}
