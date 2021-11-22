package com.xiangjing.multithreading;

import com.xiangjing.multithreading.example.ThreadUnsafeExample;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class MultithreadingApplicationTests {
    @BeforeAll
    public static  void before() {
        System.setProperty("jasypt.encryptor.password", System.getenv("JASYPT_PASSWORD"));
    }


    @Test
    void contextLoads() {
    }

    @Test
    @RepeatedTest(10)
    void test() throws InterruptedException{
        final int threadSize = 100000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }

}
