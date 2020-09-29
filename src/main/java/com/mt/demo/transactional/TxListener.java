package com.mt.demo.transactional;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by 郭俊旺 on 2020/9/29 16:40
 *
 * @author 郭俊旺
 */
@Component
@RabbitListener(queues = "txQueue")
public class TxListener {

    @RabbitHandler
    public void consumer(String msg){
        System.out.println("接受到消息==>"+msg);
    }
}
