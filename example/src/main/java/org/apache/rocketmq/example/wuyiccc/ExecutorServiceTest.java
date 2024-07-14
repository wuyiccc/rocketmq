package org.apache.rocketmq.example.wuyiccc;

import java.util.concurrent.*;

/**
 * @author wuyiccc
 * @date 2024/7/14 11:37
 */
public class ExecutorServiceTest {

    public static void main(String[] args) throws InterruptedException {


        ThreadPoolExecutor ePool = new ThreadPoolExecutor(1
                , 1
                , 10
                , TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(10)
                , new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("提交任务:" + i);
                ePool.submit(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("执行线程任务成功");
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("线程池异常" + e.getMessage());
        }


        TimeUnit.SECONDS.sleep(10);

    }
}
