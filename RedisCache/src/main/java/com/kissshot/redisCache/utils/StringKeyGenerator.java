package com.kissshot.redisCache.utils;

import org.springframework.cache.interceptor.KeyGenerator;
import java.lang.reflect.Method;
public class StringKeyGenerator implements KeyGenerator {

    private static final String NO_ARGS = "NO_ARGS";

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        if (objects.length == 0) {
            return StringKeyGenerator.NO_ARGS;
        }else if(objects.length == 1){
            Object param = objects[0];
            if(param != null && !param.getClass().isArray()){
                return param.toString();
            }
        }
        return genRedisKey(objects);
    }

    public static String genRedisKey(Object... strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
            if (i != strs.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
