package com.mt.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.plugin2.liveconnect.ArgumentHelper;

/**
 * Created by 郭俊旺 on 2020/9/27 16:49
 * direct 点对点 发送消息
 * @author 郭俊旺
 */
@Configuration
public class Config {

    /**
     * 创建交换机 direct 类型
     * */
    @Bean
    public DirectExchange directExchange(){
        //durable: 表示是否要持久化该exchange 如果不持久化,则rabbitMQ服务重启后队列会丢失
        //autoDelete: 当交换机内所有队列全部移除后是否将交换机删除
        return new DirectExchange("direct_exchange",true,false);
    }

    /**
     * 创建 队列 queue1
     * */
    @Bean
    public Queue directQueue(){
        //durable: 是否持久化队列,如果false 则重启后队列将丢失
        //autoDelete: 当消费者全部下线 是否移除队列
        return new Queue("direct_queue1",true,false,false);
    }

    /**
     * 创建 队列 queue2
     * */
    @Bean
    public Queue directQueue2(){
        return new Queue("direct_queue2",true,false,false);
    }

    /**
     * 创建绑定规则
     * */
    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("queue1");
    }

    /**
     * 创建绑定规则
     * */
    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("queue2");
    }

}
