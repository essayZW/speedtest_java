package cn.imessay.speedtest.service.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void getUserInfo() {
        System.out.println(userService.getUserInfo(1));
        assertNull(userService.getUserInfo(-1));
    }

    @Test
    void login() {
        System.out.println(userService.login("test123", "test123"));
    }
}
