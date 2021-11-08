package com.xiangjing.redis.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.*;
import com.alicp.jetcache.anno.support.ConfigProvider;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiangjing.redis.entity.User;
import com.xiangjing.redis.mapper.UserMapper;
import com.xiangjing.redis.service.JetCacheService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xiangjing
 * @version : 1.0
 * @className : JetCacheServiceUser
 * @date : 2021/11/2 - 18:27
 * @description : <>
 */
@Service
public class JetCacheServiceUser implements JetCacheService<User> {

    public static final String CACHE_NAME = "user:list";

    private final ConfigProvider configProvider;

    private UserMapper mapper;

    public JetCacheServiceUser(ConfigProvider configProvider, UserMapper userMapper) {
        this.configProvider = configProvider;
        this.mapper = userMapper;
    }

    @CreateCache(name = CACHE_NAME, expire = 3600*6, localLimit = 50 ,cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 3600, stopRefreshAfterLastAccess = 3600 * 2)
    @CachePenetrationProtect
    private Cache<List,List<User>> cache;

    @Override
    @Cached(name = CACHE_NAME, key = "{#user.id,#user.name}",expire = 3600*6, localLimit = 50, cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 3600, stopRefreshAfterLastAccess = 3600 * 2)
    @CachePenetrationProtect
    public User getUserById(User user) {
        return mapper.selectById(user.getId());
    }

    @Override
    public User updateValue(User user) {
        mapper.update(user, new UpdateWrapper<User>().eq("id", user.getId()));
        Cache<Object, Object> cache = configProvider.getCacheContext().getCache(CACHE_NAME);
        this.cache.get(new ArrayList(){{
            add(user.getId());add(user.getName());}});
        return user;
    }

    @Override
    @CacheInvalidate(name = CACHE_NAME, key="#user.id")
    public int deleteValue(User user) {
        return mapper.deleteById(user.getId());
    }
}
