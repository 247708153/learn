package com.xiangjing.designmode.creational.singleton;


/**
 * 懒汉模式
 * 线程不安全 弃用
 * @author xiangjing
 * @date 2022/07/07 17:00
 **/
@Deprecated
public class LazySingleton {

    private static LazySingleton instance = null;

    /**
     * 私有构造函数
     */
    private LazySingleton(){}

    public LazySingleton getInstance(){
        //多个线程同时执行这一步 可能new出多个实例
        if(null==instance){
            instance = new LazySingleton();
        }
        return instance;
    }
}
