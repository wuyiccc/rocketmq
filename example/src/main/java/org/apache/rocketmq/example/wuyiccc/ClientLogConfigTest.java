package org.apache.rocketmq.example.wuyiccc;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

import static org.apache.rocketmq.client.log.ClientLogger.CLIENT_LOG_LEVEL;
import static org.apache.rocketmq.client.log.ClientLogger.CLIENT_LOG_USESLF4J;

/**
 * @author wuyiccc
 * @date 2023/8/5 21:05
 */
public class ClientLogConfigTest {

    public static void main(String[] args) throws MQClientException, InterruptedException {

        // 设置开启slf4j日志, 而不是输出到rocketmq_client.log文件中
        System.setProperty(CLIENT_LOG_USESLF4J, "true");

        DefaultMQProducer producer = new DefaultMQProducer("wy_test_producer_group1");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setUnitName("unit1");
        producer.start();
        String clientId1 = producer.buildMQClientId();
        System.out.println(clientId1);




        try {
            SendResult result1 = producer.send(new Message("test20203080501", "hello localhost:9876".getBytes()));
            System.out.printf("%s%n", result1);
        } catch (Throwable e) {
            System.out.println("--------------------------first--------------------");
            e.printStackTrace();
            System.out.println("--------------------------first--------------------");
        }


        TimeUnit.SECONDS.sleep(5);
        System.out.println("sleep结束");
        producer.shutdown();

        System.out.println("结束");

    }
}

