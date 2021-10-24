package cn.imessay.speedtest.service.admin;

import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private RedisService redisService;

    public String login(String password) {
        if (!Objects.equals(password, GlobalConfig.ADMIN_PASSWORD)) {
            return null;
        }
        String sessionId = UUID.randomUUID().toString();
        sessionId = sessionId.replace("-", "");
        boolean status = redisService.set(GlobalConfig.USER_SESSION_KEY_PREFIX + GlobalConfig.ADMIN_SESSION_KEY, sessionId);
        if (status) {
            return sessionId;
        }
        return null;
    }

    public boolean logout() {
        return redisService.del(GlobalConfig.USER_SESSION_KEY_PREFIX + GlobalConfig.ADMIN_SESSION_KEY);
    }
}
