package com.mt.demo.ttl.linstener;

/**
 * Created by 郭俊旺 on 2020/9/29 14:00
 *  设置手动应答  acMode 必须为AcknowledgeMode类型
 * @author 郭俊旺
 */
//@Component
//@RabbitListener(queues = "test_ttlQueue",ackMode = "MANUAL")
//public class TtlTestLinstener {
//
//    @RabbitHandler
//    public void consumer(String body, Message message, Channel channel, @Headers Map headers) throws IOException {
//        System.out.println("接受到消息=>"+body);
//        //当不应答时 消息将被丢弃 如果设置了死信交换机则重新投递到指定的交换机中
//        //requeue 是重新入队的意思,如果为ture消息将不丢弃,重新回到队列内进行下一次消费
//        channel.basicNack(message.getMessageProperties().getDeliveryTag(),false, false);
//
//    }
//}
