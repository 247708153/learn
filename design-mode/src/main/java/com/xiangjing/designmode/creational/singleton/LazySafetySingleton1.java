package com.xiangjing.designmode.creational.singleton;


/**
 * 懒汉模式
 * 性能不太行
 * @author xiangjing
 * @date 2022/07/07 17:00
 **/
public class LazySafetySingleton1 {

    private static LazySafetySingleton1 instance = null;

    /**
     * 私有构造函数
     */
    private LazySafetySingleton1(){}

    public static synchronized LazySafetySingleton1 getInstance(){
        if(null==instance){
            instance = new LazySafetySingleton1();
        }
        return instance;
    }
}
