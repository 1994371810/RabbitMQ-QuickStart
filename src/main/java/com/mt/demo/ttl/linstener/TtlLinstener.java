package com.mt.demo.ttl.linstener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by 郭俊旺 on 2020/9/29 14:08
 *
 * @author 郭俊旺
 */
@Component
@RabbitListener(queues = "ttlQueue")
public class TtlLinstener {

    @RabbitHandler
    public void consumer(String body, Message message, Channel channel, @Headers Map headers){
        System.out.println("\n死信队列接受消息===>"+message);
    }
}
