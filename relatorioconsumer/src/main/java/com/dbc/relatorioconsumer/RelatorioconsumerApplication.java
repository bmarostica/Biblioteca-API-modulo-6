package com.dbc.relatorioconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RelatorioconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelatorioconsumerApplication.class, args);
    }

}
