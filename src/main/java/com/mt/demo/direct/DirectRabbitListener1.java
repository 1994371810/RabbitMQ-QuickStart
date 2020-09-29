package com.mt.demo.direct;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by 郭俊旺 on 2020/9/27 17:11
 * 监听  direct_queue1
 * @author 郭俊旺
 */
@Component
@RabbitListener(queues = "direct_queue1")
public class DirectRabbitListener1 {

    @RabbitHandler
    public void consumer(String result, Channel channel, @Headers Map<String,Object> header,Message message){
        System.out.println("direct_queue1接受到消息");
        System.out.println("消息体===>"+result);
        System.out.println("请求头==>"+header);
        System.out.println("消息==>"+message+"\n");
    }
}
