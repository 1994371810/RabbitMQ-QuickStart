package com.mt.demo.ttl;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 郭俊旺 on 2020/9/29 13:33
 * 参考博客 https://www.cnblogs.com/mfrank/p/11380102.html
 * @author 郭俊旺
 */
@Configuration("ttlConfig")
public class Config {
//声明接受私信的交换机和队列
    @Bean
    public DirectExchange ttlExchange(){
        return new DirectExchange("ttlExchange",true,false);
    }

    @Bean
    public Queue ttlQueue(){
        return new Queue("ttlQueue",true,false,false);
    }

    @Bean
    public Binding ttlBinding(){
        return BindingBuilder.bind(ttlQueue()).to(ttlExchange()).with("ttl");
    }

    @Bean
    public DirectExchange testTtlExchange(){
        return new DirectExchange("test_ttlExchange",true,false);
    }

    @Bean
    public Queue testTtlQueue(){
        Map argument = new HashMap();
        //设置消息在10秒未被消费后被丢弃
        argument.put("x-message-ttl",10000);

        //设置将丢弃的消息发送到 对应的交换机
        argument.put("x-dead-letter-exchange","ttlExchange");
        //设置丢弃的消息的路由key
        argument.put("x-dead-letter-routing-key","ttl");
        return new Queue("test_ttlQueue",true,false,false,argument);
    }

    @Bean
    public Binding bindTtlTestQueue(){
        return BindingBuilder.bind(testTtlQueue()).to(testTtlExchange()).with("test");
    }


}
