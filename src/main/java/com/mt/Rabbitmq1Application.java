package com.mt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mt.mapper")
public class Rabbitmq1Application {
    public static void main(String[] args) {
        SpringApplication.run(Rabbitmq1Application.class, args);
    }
}
