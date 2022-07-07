package com.xiangjing.designmode.creational.singleton;


/**
 * 懒汉模式
 * 一个错误示范 重排序
 * @author xiangjing
 * @date 2022/07/07 17:00
 **/
@Deprecated
public class LazySafetySingleton2 {

    private static LazySafetySingleton2 instance = null;

    /**
     * 私有构造函数
     */
    private LazySafetySingleton2(){}

    public static LazySafetySingleton2 getInstance(){
        if(null==instance){
            synchronized (LazySafetySingleton2.class){
                if(null==instance){
                    instance = new LazySafetySingleton2();
                }
            }
        }
        return instance;
    }
}
