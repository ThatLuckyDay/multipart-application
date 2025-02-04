package com.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SortStrategies {
    public static void main(String[] args) {
        System.out.println("sort run");
        SpringApplication.run(SortStrategies.class, args);
        System.out.println("sort stop");
    }
}
