package org.apache.rocketmq.example.wuyiccc;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.*;

/**
 * @author wuyiccc
 * @date 2023/8/10 23:04
 */
public class DefaultPullConsumerTest {

    public static void main(String[] args) throws MQClientException {

        Semaphore semaphore = new Semaphore();

        Thread t = new Thread();

    }

    static class Task implements Runnable {

        Semaphore s = new Semaphore();

        public Task(Semaphore s) {
            this.s = s;
        }

        @Override
        public void run() {
            try {
                DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("DefaultPullConsumerTestConsumer");
                consumer.setNamesrvAddr("127.0.0.1:9876");
                consumer.start();

                Map<MessageQueue, Long> offsetTable = new HashMap<>();
                // 获取该topic的所有队列
                Set<MessageQueue> msgQueueList = consumer.fetchSubscribeMessageQueues("MsgKeyTopicTest");
                if (!Objects.isNull(msgQueueList) && !msgQueueList.isEmpty()) {
                    boolean noFoundFlag = false;
                }
                while (this.s.running) {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void doSomething(List<MessageExt> msgs) {
        System.out.println("本次拉取到的消息条数: " + msgs.size());
    }



    static class Semaphore {
        public volatile boolean running = true;
    }
}

