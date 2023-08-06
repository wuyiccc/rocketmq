package org.apache.rocketmq.example.wuyiccc;

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
}
