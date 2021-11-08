package com.xiangjing.redis.config;

import com.alicp.jetcache.autoconfigure.LettuceFactory;
import com.alicp.jetcache.autoconfigure.RedisLettuceAutoConfiguration;
import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * @author : xiangjing
 * @version : 1.0
 * @className : LettuceJetcacheConfig
 * @date : 2021/11/3 - 11:16
 * @description : <使用lettuce>
 */
@Configurable
public class LettuceJetcacheConfig {

    @Bean(name = "defaultClient")
    @DependsOn(RedisLettuceAutoConfiguration.AUTO_INIT_BEAN_NAME)
    public LettuceFactory defaultClient() {
        return new LettuceFactory("remote.default", RedisClient.class);
//        使用
//        @Autowired
//        private RedisClient defaultClient;

    }
}
