package org.apache.rocketmq.example.wuyiccc;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Arrays;
import java.util.List;

/**
 * @author wuyiccc
 * @date 2023/8/6 09:12
 * tag消费测试, 被跳过tag的消息, 在监控中线上状态为CONSUMED_BUT_FILTERED(已被消费, 但是过滤掉了)
 */
public class MsgTagTest {

    public static void main(String[] args) throws Exception {

//        testProducer();

        testConsumer();
    }

    public static void testProducer() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("MsgTagTest");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        Stu stu = new Stu();
        stu.setId(1);
        stu.setNo("1");
        stu.setName("stu1");
        stu.setAge(22);

        // 用空格分隔即可对一个msg配置不同的key
        Message msg = new Message("MsgTagTopicTest", "c", "key1", JSON.toJSONBytes(stu));


        SendResult send = producer.send(msg);
        System.out.println(send);

        stu.setId(2);
        stu.setNo("2");
        stu.setName("stu2");
        stu.setAge(25);
        msg = new Message("MsgTagTopicTest", "w", "key2", JSON.toJSONBytes(stu));

        SendResult send1 = producer.send(msg);
        System.out.println(send1);


        producer.shutdown();
    }

    public static void testConsumer() throws Exception {


        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("MsgTagConsumerTest");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("MsgTagTopicTest", "c");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                if (CollectionUtils.isNotEmpty(msgs)) {

                    msgs.forEach(msg -> {
                        System.out.println("consume: " + msg.getMsgId() + ":" + msg.getTags() + ":" + new String(msg.getBody()));
                    });
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

//        consumer.shutdown();
    }

}
