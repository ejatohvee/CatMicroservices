package com.maksim.Lab3.externalInterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.maksim.Lab3.daos")
@ComponentScan(basePackages = {"com.maksim.Lab3.cat", "com.maksim.Lab3.externalInterface.Services", "com.maksim.Lab3.externalInterface.Controllers"})
@EntityScan(basePackages = {"Entities"})
public class Lab3Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab3Application.class, args);
    }
}
