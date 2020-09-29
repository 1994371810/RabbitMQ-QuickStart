package com.mt.demo.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 郭俊旺 on 2020/9/27 18:05
 *
 * @author 郭俊旺
 */
@RestController
@RequestMapping("/fanout")
public class FanoutController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send(String msg){
        rabbitTemplate.convertAndSend("fanout_exchange","",msg);
        return "SUCCESSFUL";
    }

}
