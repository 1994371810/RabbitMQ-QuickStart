package com.mt.demo.transactional;

import com.mt.demo.transactional.service.SendService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 郭俊旺 on 2020/9/29 16:22
 *
 * @author 郭俊旺
 */
@RestController
public class TxController {
    @Autowired
    private SendService sendService;

    @GetMapping("/tx")
    public String send(String msg){
        System.out.println("发送消息==>"+msg);
        sendService.send(msg);
        return "success";
    }

}
