package com.xiangjing.redis;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xiangjing.redis.mapper")
@SpringBootApplication
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.xiangjing.redis.service.impl")
public class RedisApplication {

    // test
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}
