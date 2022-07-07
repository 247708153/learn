package com.xiangjing.designmode.creational.singleton;


/**
 * 懒汉模式
 * 还行
 * @author xiangjing
 * @date 2022/07/07 17:00
 **/
public class LazySafetySingleton3 {

    private static volatile LazySafetySingleton3 instance = null;

    /**
     * 私有构造函数
     */
    private LazySafetySingleton3(){}

    public static LazySafetySingleton3 getInstance(){
        if(null==instance){
            synchronized (LazySafetySingleton3.class){
                if(null==instance){
                    instance = new LazySafetySingleton3();
                }
            }
        }
        return instance;
    }
}
