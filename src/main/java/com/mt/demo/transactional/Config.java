package com.mt.demo.transactional;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import  org.springframework.amqp.core.*;
import org.springframework.validation.BindingResult;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by 郭俊旺 on 2020/9/29 15:55
 * rabbitMQ 通过事务进行发送消息
 * @author 郭俊旺

RabbitMQ中的事务使用起来虽然简单，但是对性能的影响是不可忽视的，
因为每次事务的提交都是阻塞式的等待服务器处理返回结果，而默认模式下，
客户端是不需要等待的，直接发送就完事了，除此之外，事务消息需要比普通消息多4次与服务器的交互，
这就意味着会占用更多的处理时间，所以如果对消息处理速度有较高要求时，尽量不要采用事务机制。

 * 参考博客: https://www.cnblogs.com/mfrank/p/11380102.html
 */
@Configuration("txConfig")
public class Config {

    @Bean
    public DirectExchange txExchange(){
        return new DirectExchange("txExchange",true,false);
    }

    @Bean
    public Queue txQueue(){
        return new Queue("txQueue",true,false,false);
    }

    @Bean
    public Binding txBind(){
        return BindingBuilder.bind(txQueue()).to(txExchange()).with("tx");
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //1开启事务
/*    @PostConstruct
    public void config(){
        rabbitTemplate.setChannelTransacted(true);
    }*/


    //2注入事务管理器
/*    @Bean
    public RabbitTransactionManager rabbitTransactionManager(@Autowired CachingConnectionFactory cachingConnectionFactory){
        return new RabbitTransactionManager(cachingConnectionFactory);
    }*/

}
