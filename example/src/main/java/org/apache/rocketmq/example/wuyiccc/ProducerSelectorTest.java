package org.apache.rocketmq.example.wuyiccc;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @author wuyiccc
 * @date 2023/8/6 08:15
 *
 * 指定queueSelector不会走失败重试逻辑测试
 */
public class ProducerSelectorTest {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("ProducerSelectorTest");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        Stu stu = new Stu();
        stu.setId(1);
        stu.setNo("1");
        stu.setName("stu1");
        stu.setAge(22);

        sendMsg(producer, stu);

        producer.shutdown();
    }

    private static void sendMsg(DefaultMQProducer producer, Stu stu) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {

        // 使用no作为key, 方便进行查询
        Message msg = new Message("ProducerSelectorTopicTest", null, stu.getNo(), JSON.toJSONString(stu).getBytes());
        producer.send(msg, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                if (CollectionUtils.isEmpty(mqs)) {
                    return null;
                }

                int index = (Math.abs(arg.hashCode())) % mqs.size();
                return mqs.get(Math.max(index, 0));
            }
        }, stu.getNo());
    }
}

