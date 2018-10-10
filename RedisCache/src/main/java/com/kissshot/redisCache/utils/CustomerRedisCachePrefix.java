package com.kissshot.redisCache.utils;

import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class CustomerRedisCachePrefix implements RedisCachePrefix {

    private final RedisSerializer<String> serializer = new StringRedisSerializer();
    public static final String single = "";
    public static final String delimiter = ":";
    public static final String prefix = "Cache-Redis:";

    @Override
    public byte[] prefix(String s) {
       return serializer.serialize(prefix.concat(s.concat(delimiter)));
    }
}
