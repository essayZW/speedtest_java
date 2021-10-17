package cn.imessay.speedtest.service.cidr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CidrServiceTest {
    @Autowired
    private CidrService cidrService;

    @Test
    void getFirstMatched() {
        assertNotNull(cidrService.getFirstMatched("192.168.0.3"));
        assertNull(cidrService.getFirstMatched("212.123.45.12"));
        assertNotNull(cidrService.getFirstMatched("10.0.0.0"));
    }

    @Test
    void getMatched() {
        assertEquals(1, cidrService.getMatched("10.0.0.0").size());
    }
}
