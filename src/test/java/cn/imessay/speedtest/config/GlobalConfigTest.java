package cn.imessay.speedtest.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GlobalConfigTest {

    @Test
    void set() throws NoSuchFieldException {

        Field field = GlobalConfig.class.getField("MAX_IP_API_AWAIT_TIME");
        GlobalConfig.set(field, 4);
    }


    @Test
    void get() {
        assertEquals(4, GlobalConfig.MAX_IP_API_AWAIT_TIME);
    }
}
