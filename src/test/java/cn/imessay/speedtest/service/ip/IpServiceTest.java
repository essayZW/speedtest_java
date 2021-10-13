package cn.imessay.speedtest.service.ip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IpServiceTest {
    @Autowired
    private IpService ipService;

    @Test
    void getInfo() {
        System.out.println(ipService.getInfo("219.242.110.62"));
        System.out.println(ipService.getInfo("10.0.0.1"));
    }
}
