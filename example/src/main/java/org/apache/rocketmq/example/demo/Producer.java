package org.apache.rocketmq.example.demo;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author wuyiccc
 * @date 2024/5/14 20:38
 */
public class Producer {


    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("demo_test_producer");


        producer.setNamesrvAddr("localhost:9876");

        producer.start();


        for (int i = 0; i < 1; i++) {

            try {
                Message msg = new Message("TopicTest", "TagA", ("Hello RocketMQ " + i).getBytes(StandardCharsets.UTF_8));
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                TimeUnit.SECONDS.sleep(1);
            }
        }
        producer.shutdown();


    }
}
