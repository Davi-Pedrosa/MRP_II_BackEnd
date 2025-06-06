package com.mrp2.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.mrp2.backend.repository")
@EntityScan(basePackages = "com.mrp2.backend.model")
public class Mrp2BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(Mrp2BackendApplication.class, args);
    }
} 