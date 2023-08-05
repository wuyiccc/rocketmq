package org.apache.rocketmq.example.wuyiccc;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * @author wuyiccc
 * @date 2023/8/5 22:27
 */
public class ProducerAsyncTest {

    public static void main(String[] args) throws InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("ProducerAsyncTest");
        producer.setNamesrvAddr("localhost:9876");

        try {
            producer.start();

            Message msg = new Message("TopicTest", "hello rocketmq".getBytes());
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("produce success:" + sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println("produce failed" + e.toString());
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }

        TimeUnit.SECONDS.sleep(3);
        producer.shutdown();
    }
}
