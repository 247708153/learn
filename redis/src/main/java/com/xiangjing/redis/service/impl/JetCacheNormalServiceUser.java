package com.xiangjing.redis.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.*;
import com.alicp.jetcache.anno.support.ConfigProvider;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiangjing.redis.entity.User;
import com.xiangjing.redis.mapper.UserMapper;
import com.xiangjing.redis.service.JetCacheService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : xiangjing
 * @version : 1.0
 * @className : JetCacheServiceUser
 * @date : 2021/11/2 - 18:27
 * @description : <>
 */
@Service
public class JetCacheNormalServiceUser implements JetCacheService<User> {

    public static final String CACHE_NAME = "user:list";

    private final ConfigProvider configProvider;

    private UserMapper mapper;

    @CreateCache(name = CACHE_NAME, expire = 3600*6, localLimit = 50 ,cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 3600, stopRefreshAfterLastAccess = 3600 * 2)
    @CachePenetrationProtect
    private Cache<String,List<User>> cache;

    public JetCacheNormalServiceUser(ConfigProvider configProvider, UserMapper userMapper) {
        this.configProvider = configProvider;
        this.mapper = userMapper;
    }

    @Override
    @Cached(name = CACHE_NAME, key = "#user.id",expire = 3600*6, localLimit = 50, cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 3600, stopRefreshAfterLastAccess = 3600 * 2)
    @CachePenetrationProtect
    public User getUserById(User user) {
        return mapper.selectById(user.getId());
    }

    @Override
    @CacheUpdate(name = CACHE_NAME, key="#user.id", value="#user")
    public User updateValue(User user) {
        mapper.update(user, new UpdateWrapper<User>().eq("id", user.getId()));
        return user;
    }

    @Override
    @CacheInvalidate(name = CACHE_NAME, key="#user.id")
    public int deleteValue(User user) {
        return mapper.deleteById(user.getId());
    }
}
