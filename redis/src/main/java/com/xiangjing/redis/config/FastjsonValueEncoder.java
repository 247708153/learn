package com.xiangjing.redis.config;

import com.alibaba.fastjson.JSON;
import com.alicp.jetcache.support.AbstractValueEncoder;
import com.alicp.jetcache.support.CacheEncodeException;
import org.springframework.stereotype.Component;

@Component
public class FastjsonValueEncoder extends AbstractValueEncoder {

    @SuppressWarnings("deprecation")
    public static final FastjsonValueEncoder INSTANCE = new FastjsonValueEncoder(true);

    protected static int IDENTITY_NUMBER = 0x4A953A81;

    public FastjsonValueEncoder() {
        super(true);
    }

    public FastjsonValueEncoder(boolean useIdentityNumber) {
        super(useIdentityNumber);
    }

    @Override
    public byte[] apply(Object value) {
        try {
            byte[] bs1 = JSON.toJSONString(value).getBytes();
//            byte[] bs1 = JSON.toJSONBytes(value, SerializerFeature.WriteClassName);

            if (useIdentityNumber) {
                byte[] bs2 = new byte[bs1.length + 4];
                writeHeader(bs2, IDENTITY_NUMBER);
                System.arraycopy(bs1, 0, bs2, 4, bs1.length);
                return bs2;
            } else {
                return bs1;
            }
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Fastjson Encode error. ");
            sb.append("msg=").append(e.getMessage());
            throw new CacheEncodeException(sb.toString(), e);
        }
    }
}