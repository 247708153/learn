package com.xiangjing.designmode.creational.simpleFactory;


/**
 * 工厂类
 *
 * @author xiangjing
 * @date 2022/08/01 16:42
 **/
public class LunchFactory {

    public static Lunch ChinaLunch(){
        return new ChinaLunch();
    }

    public static Lunch AmericaLunch(){
        return new AmericaLunch();
    }
}
