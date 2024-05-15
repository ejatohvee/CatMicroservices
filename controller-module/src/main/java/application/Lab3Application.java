package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "Controllers")
@ComponentScan(basePackages = "Application.Application.Services")
@ComponentScan(basePackages = "Application.EntityMappers")
@EnableJpaRepositories(basePackages = "Application.Abstractions.Repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "Application.Entities")
public class Lab3Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab3Application.class, args);
    }
}