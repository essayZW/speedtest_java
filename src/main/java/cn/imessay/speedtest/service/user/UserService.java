package cn.imessay.speedtest.service.user;

import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.dao.user.UserDAO;
import cn.imessay.speedtest.dao.user.UserDO;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import cn.imessay.speedtest.pojo.vo.UserVO;
import cn.imessay.speedtest.service.redis.RedisService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RedisService redisService;

    private Logger logger = LoggerFactory.getLogger(UserService.class);
    public UserDTO getUserInfo(int id) {
        UserDO userDO = userDAO.query(id);
        if (userDO == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userDO.getUsername());
        userDTO.setId(userDO.getId());
        userDTO.setExtraAttribute(userDO.getExtraAttribute());
        return userDTO;
    }

    public Integer getUserIdByUsername(String username) {
        return userDAO.queryId(username);
    }

    public Integer addUser(UserVO userVO) {
        if (userVO == null) return null;

        // 将查询和插入合并成一个原子操作, 保证用户名的唯一性
        synchronized (userVO.getUsername().intern()) {
            Integer id = getUserIdByUsername(userVO.getUsername());
            if (id != null) return null;

            UserDO userDO = new UserDO();
            userDO.setUsername(userVO.getUsername());
            userDO.setExtraAttribute(JSONObject.parseObject(userVO.getExtraAttribute()));
            userDO.setPassword(userVO.getPassword());
            return userDAO.insert(userDO);
        }
    }


    public String login(String username, String password) {
        return login(username, password, null);
    }

    public String login(String username, String password, UserDTO loginedUserInfo) {
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        String md5Password = DigestUtils.md5DigestAsHex(passwordBytes);
        UserDO userDO = userDAO.query(username, md5Password);
        if (userDO == null) {
            logger.info("Login Fail||{}||{}", username, md5Password);
            return null;
        }
        String sessionId = UUID.randomUUID().toString();
        sessionId = sessionId.replace("-", "");
        redisService.set(GlobalConfig.USER_SESSION_KEY_PREFIX + sessionId,
                userDO.getId(),
                GlobalConfig.USER_SESSION_EXPIRE_SECONDS,
                TimeUnit.SECONDS);
        if (loginedUserInfo != null) {
            loginedUserInfo.setUsername(userDO.getUsername());
            loginedUserInfo.setId(userDO.getId());
            loginedUserInfo.setExtraAttribute(userDO.getExtraAttribute());
        }
        return sessionId;
    }
}
