package com.mt.demo.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 郭俊旺 on 2020/9/27 18:15
 * 以匹配的形式发送消息
 *  # 匹配多个路径  如  message.# 可以匹配 message.a, message.a.b, message.a.b.c....
 *  * 匹配1个路径  如  message.* 可以匹配 message.a, message.b 不能匹配 message.a.b
 * @author 郭俊旺
 */
@Configuration("topicConfig")
public class Config {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic_exchange",true,false);
    }

    @Bean
    public Queue messageQueue(){
        return new Queue("message.*",true,false,false);
    }

    @Bean
    public Queue logQueue(){
        return new Queue("log.*",true,false,false);
    }

    @Bean
    public Queue infoQueue(){
        return new Queue("*.info",true,false,false);
    }

    @Bean
    public Queue warnQueue(){
        return new Queue("*.warn",true,false,false);
    }

    @Bean
    public Binding topicBind(){
        return BindingBuilder.bind(messageQueue()).to(topicExchange()).with("message.*");
    }

    @Bean
    public Binding topicBind2(){
        return BindingBuilder.bind(logQueue()).to(topicExchange()).with("log.*");
    }

    @Bean
    public Binding topicBind3(){
        return BindingBuilder.bind(warnQueue()).to(topicExchange()).with("*.warn");
    }


    @Bean
    public Binding topicBind4(){
        return BindingBuilder.bind(infoQueue()).to(topicExchange()).with("*.info");
    }
}
