package cn.imessay.speedtest.service.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    void get() {
        redisService.set("123test", "123");
        redisService.set("expirekey", "xss", 120L, TimeUnit.SECONDS);
    }

    @Test
    void set() {
        assertEquals("123", redisService.get("123test").toString());
    }
}
