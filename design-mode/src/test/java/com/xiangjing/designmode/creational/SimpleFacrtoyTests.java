package com.xiangjing.designmode.creational;

import com.xiangjing.designmode.creational.simpleFactory.Lunch;
import com.xiangjing.designmode.creational.simpleFactory.LunchFactory;
import com.xiangjing.designmode.creational.singleton.HungerSingleton;
import com.xiangjing.designmode.creational.singleton.InnerSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleFacrtoyTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void  hunger(){
        Lunch chinaLunch = LunchFactory.ChinaLunch();
        chinaLunch.eat();
        LunchFactory.AmericaLunch().eat();
    }

}
