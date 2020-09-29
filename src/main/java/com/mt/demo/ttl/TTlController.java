package com.mt.demo.ttl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 郭俊旺 on 2020/9/29 14:09
 *
 * @author 郭俊旺
 */
@RestController
public class TTlController {
    private int i = 0;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 在投递的时候也可以设置消息的过期时间,如果超过了这个时间则丢弃
     * 通过 message设置过期时间的方式,只有在投递时才会判断是否过期
     * 而queue内设置的过期时间,是扫描所有消息判断是否过期
     *
     * 如果使用在消息属性上设置TTL的方式，消息可能并不会按时“死亡“，
     * 因为RabbitMQ只会检查第一个消息是否过期，如果过期则丢到死信队列，
     * 所以如果第一个消息的延时时长很长，而第二个消息的延时时长很短，则第二个消息并不会优先得到执行
     * */
    @GetMapping("ttl")
    public void send(String time){
        rabbitTemplate.convertAndSend("test_ttlExchange", "test", "消息" + time, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
                messageProperties.setExpiration(time);
                return message;
            }
        });
    }
}
