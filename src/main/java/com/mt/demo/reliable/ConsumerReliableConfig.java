package com.mt.demo.reliable;

/**
 * @author 郭俊旺
  Created by 郭俊旺 on 2020/9/28 10:53

  参考博客 RabbitMQ 重试机制和消息幂等性
  https://blog.csdn.net/zxc123e/article/details/89963756

  通过@RabbitListener 进行消费时,会通过aop进行异常捕获,当发送异常时会对消息进行重试
  我们只需要进行配置即可

     #设置linstener的重试机制,当listener再处理消息抛出异常时进行重试
     spring.rabbitmq.listener.simple.retry.enabled=true
     #重试3次
     Spring.rabbitmq.template.retry.max-attempts=3
     #每次重试的间隔
     spring.rabbitmq.listener.simple.retry.initial-interval=10000

 */
public class ConsumerReliableConfig {
}
