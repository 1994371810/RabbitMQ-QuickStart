package com.mt.demo.topic;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 郭俊旺 on 2020/9/27 18:42
 *
 * @author 郭俊旺
 */
@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send(String routingKey,String msg){
        System.out.println("发送==>"+routingKey);
        rabbitTemplate.convertAndSend("topic_exchange2adfaffasf",routingKey,msg, new MessagePostProcessor(){

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
               messageProperties.setMessageId("1");
                return message;
            }
        }, new CorrelationData("aa"));
        return "success";
    }

}
