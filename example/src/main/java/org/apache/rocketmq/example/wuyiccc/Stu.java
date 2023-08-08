package org.apache.rocketmq.example.wuyiccc;

import org.apache.rocketmq.common.message.MessageQueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wuyiccc
 * @date 2023/8/6 09:07
 */
public class Stu {

    private Integer id;

    private String no;

    private String name;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }


    @Override
    public String toString() {
        return "Stu{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        ConcurrentMap<String, String> offsetTable =
                new ConcurrentHashMap<>();

        String old = offsetTable.putIfAbsent("key1", "value1");
        System.out.println(old);
        String old2 = offsetTable.putIfAbsent("key1", "value2");
        System.out.println(old2);

        System.out.println(offsetTable.get("key1"));

    }
}
