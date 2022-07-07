package com.xiangjing.designmode.creational.singleton;


/**
 * 饿汉模式
 *
 * @author xiangjing
 * @date 2022/07/07 17:00
 **/
public class HungerSingleton {

    private static final HungerSingleton instance = new HungerSingleton();

    /**
     * 私有构造函数
     */
    private HungerSingleton(){}

    public static HungerSingleton getInstance(){
        return instance;
    }
}
