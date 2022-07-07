package com.xiangjing.designmode.creational.singleton;


/**
 * 静态内部类
 * effective java 推荐
 * @author xiangjing
 * @date 2022/07/07 17:28
 **/
public class InnerSingleton {

    private InnerSingleton(){}

    private static class SingletonHolder {
        private static final InnerSingleton instance = new InnerSingleton();
    }

    public static InnerSingleton getInstance(){
        return SingletonHolder.instance;
    }
}
