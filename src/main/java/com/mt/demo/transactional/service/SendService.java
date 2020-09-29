package com.mt.demo.transactional.service;

import org.apache.ibatis.jdbc.Null;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 郭俊旺 on 2020/9/29 16:25
 *
 * @author 郭俊旺
 */
@Service
public class SendService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional(rollbackFor = Throwable.class)
    public void send(String msg){
        rabbitTemplate.convertAndSend("txExchange","tx",msg);
        if(msg.contains("error")){
            throw new NullPointerException();
        }
    }
}
