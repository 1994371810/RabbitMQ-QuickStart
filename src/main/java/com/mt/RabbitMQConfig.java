package com.mt;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 郭俊旺 on 2020/9/27 16:40
 *
 * @author 郭俊旺
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange orderExchange(){
        return new DirectExchange("order",true,false);
    }

}
