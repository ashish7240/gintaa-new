package com.swiggy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SwiggyApp {
    public static void main(String[] args) {
        int x = 10;

        SpringApplication.run(SwiggyApp.class, args);
    }
}