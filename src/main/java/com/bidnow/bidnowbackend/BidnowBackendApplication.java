package com.bidnow.bidnowbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableScheduling
public class BidnowBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BidnowBackendApplication.class, args);
    }
}
