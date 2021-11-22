package com.xiangjing.multithreading.example;

/**
 * @author : xiangjing
 * @version : 1.0
 * @className : ThreadUnsafeExample
 * @date : 2021/11/20 - 19:01
 * @description : <>
 */
public class ThreadUnsafeExample {

    private int cnt = 0;

    public void add() {
        cnt++;
    }

    public int get() {
        return cnt;
    }
}
