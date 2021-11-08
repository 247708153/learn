package com.xiangjing.redis.config;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class JsonEncoder implements Function<Object, byte[]> {


    @Override
    public byte[] apply(Object o) {
        return JSON.toJSONBytes(o);
    }
}