package com.mt.demo.reliable;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 郭俊旺 on 2020/9/28 9:10
 * 消息可靠投递

 * 要确保消息的可靠投递需要进行配置

   一 开启(2中方法):
        1 可以通过配置文件配置:
         #开启returnFllback
         spring.rabbitmq.publisher-returns=true
         #开启ConfirmFallback
         spring.rabbitmq.publisher-confirm-type=CORRELATED
         spring.rabbitmq.template.mandatory=true

        2 通过注入自己的ConnectoryFactory 配置
         @Bean
         public CachingConnectionFactory connectionFactory(){
             CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
             connectionFactory.setHost("47.115.80.90");
             connectionFactory.setPort(5672);
             connectionFactory.setUsername("guest");
             connectionFactory.setPassword("guest");
             connectionFactory.setConnectionTimeout(1000);

             connectionFactory.setPublisherReturns(true);
             connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
             return connectionFactory;
         }

    二 设置RabbitTemplate的 confirm 和 return 的回调函数
         returnCallback: 消息成功到达交换机 当为匹配到队列(消息无队列消费)
         // returnCallback 必须设置 mandatory
         rabbitTemplate.setMandatory(true);

         rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
         @Override
         public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
         System.out.println("交换机==>"+exchange);
         System.out.println("routingKey==>"+routingKey);
         System.out.println("replyText===>"+replyText);
         System.out.println("replyCode===>"+replyCode);
         System.out.println("message===>"+message);
         }
         });



 * @author 郭俊旺
 */
@Configuration("reliableConfig")
public class SendReliableConfig {


    @Bean
    public CachingConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("47.115.80.90");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setConnectionTimeout(1000);

        connectionFactory.setPublisherReturns(true);
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        // returnCallback 必须设置 mandatory
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("\n returnCallback");
                System.out.println("交换机==>"+exchange);
                System.out.println("routingKey==>"+routingKey);
                System.out.println("replyText===>"+replyText);
                System.out.println("replyCode===>"+replyCode);
                System.out.println("message===>"+message);
                System.out.println("\n\n");
            }
        });

        /**
         * correlationData 再发送消息时可以设置
         * @link( com.mt.demo.topic.TopicController.send)
         * */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
               if(!ack){

                   System.out.println("\n confirmCallback");
                   System.out.println("未选择交换机");
                   //  Message message = correlationData.getReturnedMessage();
                   System.out.println("message==>"+correlationData);
                   System.out.println("cause=>"+cause);
                   System.out.println("\n\n");
               }
            }
        });

        return rabbitTemplate;
    }




}
