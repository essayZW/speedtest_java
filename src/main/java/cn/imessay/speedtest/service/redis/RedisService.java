package cn.imessay.speedtest.service.redis;

import cn.imessay.speedtest.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {


    @PostConstruct
    public void init() {
        GlobalConfig.setRedisService(this);
        GlobalConfig.initConfig();
    }


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public Object get(Object key) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        Object value = ops.get(key);
        return value;
    }

    public boolean set(Object key, Object value) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        try {
            ops.set(key, value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean set(Object key, Object value, Long time, TimeUnit timeUnit) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        try {
            ops.set(key, value, time, timeUnit);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
