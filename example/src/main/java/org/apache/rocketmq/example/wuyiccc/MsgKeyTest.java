package org.apache.rocketmq.example.wuyiccc;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author wuyiccc
 * @date 2023/8/6 09:07
 * 为消息指定key, 方便搜索
 */
public class MsgKeyTest {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("MsgKeyTest");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        Stu stu = new Stu();
        stu.setId(1);
        stu.setNo("1");
        stu.setName("stu1");
        stu.setAge(22);

        // 用空格分隔即可对一个msg配置不同的key
        Message msg = new Message("MsgKeyTopicTest", null, "key1 key2",JSON.toJSONBytes(stu));

        SendResult send = producer.send(msg);
        System.out.println(send);


        producer.shutdown();
    }
}
