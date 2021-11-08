package com.xiangjing.redis.service;

/**
 * @author : xiangjing
 * @version : 1.0
 * @className : JetCacheService
 * @date : 2021/11/2 - 18:15
 * @description : <service>
 */
public interface JetCacheService<T> {
    /**
     * @return
     */
    T getUserById(T t);

    /**
     * 更新缓存
     * @param t
     * @return
     */
    T updateValue(T t);

    /**
     * 删除缓存
     * @param t
     * @return
     */
    int deleteValue(T  t);

}
