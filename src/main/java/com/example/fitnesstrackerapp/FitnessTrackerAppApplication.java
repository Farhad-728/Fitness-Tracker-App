package com.example.fitnesstrackerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class FitnessTrackerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitnessTrackerAppApplication.class, args);
    }

}
