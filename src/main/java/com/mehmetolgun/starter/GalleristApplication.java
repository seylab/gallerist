package com.mehmetolgun.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = "com.mehmetolgun")
@EntityScan(basePackages = "com.mehmetolgun")
@EnableJpaRepositories(basePackages = "com.mehmetolgun")
@SpringBootApplication
public class GalleristApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleristApplication.class, args);
    }

}
