package com.mt.direct;

import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 郭俊旺 on 2020/9/27 16:55
 *
 * @author 郭俊旺
 */
@RestController
@RequestMapping("/direct")
public class DirectController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * */
    @GetMapping("/send/{routingKey}")
    public String send(@PathVariable String routingKey, String msg ){
        System.out.println("发送消息=>"+routingKey);
        rabbitTemplate.convertAndSend("direct_exchange",routingKey,msg);
        return "success";
    }



}
