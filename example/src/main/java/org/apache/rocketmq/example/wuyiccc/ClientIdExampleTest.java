package org.apache.rocketmq.example.wuyiccc;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * @author wuyiccc
 * @date 2023/8/5 17:14
 * clientId 同进程重复问题测试, v4.9.1版本已被修复 加了nanoTimes, 如果是其他版本, 最好设置unitName
 */
public class ClientIdExampleTest {

    public static void main(String[] args) throws MQClientException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("wy_test_producer_group1");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setUnitName("unit1");
        producer.start();
        String clientId1 = producer.buildMQClientId();
        System.out.println(clientId1);


        DefaultMQProducer producer2 = new DefaultMQProducer("wy_test_producer_group1");
        producer2.setNamesrvAddr("wuji.local.wuyiccc.com:13001");
        producer2.setUnitName("unit2");
        producer2.start();
        String clientId2 = producer2.buildMQClientId();
        System.out.println(clientId2);

        try {
            SendResult result1 = producer.send(new Message("test20203080501", "hello localhost:9876".getBytes()));
            System.out.printf("%s%n", result1);
        } catch (Throwable e) {
            System.out.println("--------------------------first--------------------");
            e.printStackTrace();
            System.out.println("--------------------------first--------------------");
        }

        try {
            SendResult result2 = producer2.send(new Message("test20203080502", "hello wuji.local.wuyiccc.com:13001".getBytes()));
            System.out.printf("%s%n", result2);
        } catch (Throwable e) {
            System.out.println("--------------------------second--------------------");
            e.printStackTrace();
            System.out.println("--------------------------second--------------------");
        }

        TimeUnit.SECONDS.sleep(5);

    }
}
