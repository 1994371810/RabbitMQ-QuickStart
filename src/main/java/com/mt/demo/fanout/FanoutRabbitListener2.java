package com.mt.demo.fanout;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by 郭俊旺 on 2020/9/27 18:08
 *
 * @author 郭俊旺
 */
@Component
@RabbitListener(queues = "fanout_queue2")
public class FanoutRabbitListener2 {

    @RabbitHandler
    public void consumer(String result, Message message, Channel channel, @Headers Map<String,Object> header){
        System.out.println("fanout_queue2 接受到消息");
        System.out.println("消息体===>"+result);
        System.out.println("请求头==>"+header);
        System.out.println("消息==>"+message+"\n");

    }
}
