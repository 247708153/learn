package com.xiangjing.designmode;

import com.xiangjing.designmode.creational.singleton.HungerSingleton;
import com.xiangjing.designmode.creational.singleton.InnerSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SingletonTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void  hunger(){
        HungerSingleton hunger1 = HungerSingleton.getInstance();
        HungerSingleton hunger2 = HungerSingleton.getInstance();
        System.out.println(hunger1==hunger2);
    }

    @Test
    public void  inner(){
        InnerSingleton inner1 = InnerSingleton.getInstance();
        InnerSingleton inner2 = InnerSingleton.getInstance();
        System.out.println(inner1==inner2);
    }
}
