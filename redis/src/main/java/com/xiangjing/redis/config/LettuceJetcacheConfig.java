package com.xiangjing.redis.config;

import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.autoconfigure.LettuceFactory;
import com.alicp.jetcache.autoconfigure.RedisLettuceAutoConfiguration;
import io.lettuce.core.RedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.function.Function;

/**
 * @author : xiangjing
 * @version : 1.0
 * @className : LettuceJetcacheConfig
 * @date : 2021/11/3 - 11:16
 * @description : <使用lettuce>
 */
@Configuration
public class LettuceJetcacheConfig {

    @Bean(name = "defaultClient")
    @DependsOn(RedisLettuceAutoConfiguration.AUTO_INIT_BEAN_NAME)
    public LettuceFactory defaultClient() {
        return new LettuceFactory("remote.default", RedisClient.class);
//        使用
//        @Autowired
//        private RedisClient defaultClient;

    }

    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider() {
            @Override
            public Function<Object, byte[]> parseValueEncoder(String valueEncoder) {
                if(valueEncoder.equals("yuanc")){
                    return new FastjsonValueEncoder();
                }else{
                    return super.parseValueEncoder(valueEncoder);
                }
            };
            @Override
            public Function<byte[], Object> parseValueDecoder(String valueDecoder) {
                if(valueDecoder.equals("yuanc")){
                    return new FastjsonValueDecoder();
                }else{
                    return super.parseValueDecoder(valueDecoder);
                }
            }
        };
    }
}
