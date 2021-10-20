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

    public void set(Object key, Object value) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    public void set(Object key, Object value, Long time, TimeUnit timeUnit) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value, time, timeUnit);
    }
}
