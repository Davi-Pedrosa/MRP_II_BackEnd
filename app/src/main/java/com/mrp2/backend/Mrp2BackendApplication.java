package com.mrp2.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Mrp2BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(Mrp2BackendApplication.class, args);
    }
} 