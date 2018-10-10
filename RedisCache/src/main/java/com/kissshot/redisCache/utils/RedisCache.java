package com.kissshot.redisCache.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import java.util.concurrent.Callable;

public class RedisCache implements Cache {
    private RedisTemplate<String,Object> redisTemplate;
    private String  name;
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper get(Object o) {
        System.out.println("-----获取缓存----");
        final String key = o.toString();
        Object obj = null;
        obj = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] keyb = key.getBytes();
                byte[] value = redisConnection.get(keyb);
                if (value == null) {
                    System.out.println("----- 缓存不存在-----");
                    return  null;
                }
                return SerializationUtils.deserialize(value);
            }
        });

        ValueWrapper wrapper = (obj != null ? new SimpleValueWrapper(obj) : null);
        System.out.println("----获取到内容-----"+wrapper);
        return wrapper;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        System.out.println("-----加入缓存------");
        System.out.println("key----:"+ key);
        final String keyString = key.toString();
        final  Object vaulef = value;
        final  long timeLive = 86400;
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] keyb = keyString.getBytes();
                byte[] valueb = SerializationUtils.serialize(vaulef);
                redisConnection.set(keyb,valueb);
                if (timeLive > 0) {
                    redisConnection.expire(keyb,timeLive);
                }
                return 1L;
            }
        });
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    @Override
    public void evict(Object o) {
        System.out.println("----缓存删除-----");
        final String key = o.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.del(key.getBytes());
            }
        });
    }

    @Override
    public void clear() {
        System.out.println("----缓存删除-----");
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();
                return "OK";
            }
        });
    }
}
