package org.apache.rocketmq.example.wuyiccc;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.ByteBuffer;

import static org.apache.rocketmq.client.log.ClientLogger.CLIENT_LOG_USESLF4J;

/**
 * @author wuyiccc
 * @date 2023/8/6 10:05
 * 同一个msg在发送的时候, 如果发送失败之后重试, msgId不变, msg发送成功了, 但是客户端判断失败之后产生的效果
 * 有三个相同的msgId的消息, 但是offsetMsgId和queueOffset都不同
 *
 * offsetMsgId生成方式见
 * {@link org.apache.rocketmq.store.CommitLog.DefaultAppendMessageCallback#doAppend} 中的Supplier<String> msgIdSupplier
 * 规则为brokerIp:port + 物理偏移量
 */
public class MsgIdTest {

    public static void main(String[] args) {

        System.setProperty(CLIENT_LOG_USESLF4J, "true");

        DefaultMQProducer producer = new DefaultMQProducer("MsgIdTest");
        try {

            producer.setNamesrvAddr("localhost:9876");
            producer.start();

            Stu stu = new Stu();
            stu.setId(1);
            stu.setNo("1");
            stu.setName("stu1");
            stu.setAge(22);

            // 用空格分隔即可对一个msg配置不同的key
            Message msg = new Message("MsgIdTopicTest", null, "key1 key2", JSON.toJSONBytes(stu));

            SendResult send = producer.send(msg);
            System.out.println(send);

            String msgId = send.getMsgId();
            System.out.println("msgId: " + msgId);
        } catch (Exception e) {
            System.out.println("发送消息失败");
        } finally {
            producer.shutdown();
        }



    }
}
