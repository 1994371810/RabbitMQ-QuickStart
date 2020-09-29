package com.mt.demo.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 郭俊旺 on 2020/9/27 17:38
 *  fanout 对所有队列 发送消息
 * @author 郭俊旺
 */
@Configuration("fanoutConfig")
public class Config {

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_exchange",true,false);
    }

    @Bean
    public Queue queue1(){
        return new Queue("fanout_queue1",true,false,false);
    }

    @Bean
    public Queue queue2(){
        return new Queue("fanout_queue2",true,false,false);
    }

    @Bean
    public Binding fanout_binding1(){
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanout_binding2(){
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }

}
