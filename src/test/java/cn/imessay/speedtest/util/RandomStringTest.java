package cn.imessay.speedtest.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomStringTest {

    @Test
    void generate() {
        assertEquals(128, RandomString.generate(128).length());
        assertEquals(64, RandomString.generate(64).length());
        assertEquals(0, RandomString.generate(0).length());
    }
}
