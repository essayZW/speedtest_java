package cn.imessay.speedtest.dao.history;

import cn.imessay.speedtest.pojo.dto.UserDTO;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class SpeedHistoryDAOTest {
    @Autowired
    private SpeedHistoryDAO speedHistoryDAO;

    @Test
    void insert() {
        SpeedHistoryDO speedHistoryDO = new SpeedHistoryDO();
        Random random = new Random();
        speedHistoryDO.setIp("127.0.0.1");
        speedHistoryDO.setUa("Useragent");
        speedHistoryDO.setDl(random.nextFloat());
        speedHistoryDO.setUl(random.nextFloat());
        speedHistoryDO.setPing(random.nextFloat());
        speedHistoryDO.setJitter(random.nextFloat());
        speedHistoryDO.setTestPointId(1);
        speedHistoryDO.setExtraAttribute(JSONObject.parseObject("{\"a\":1}"));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(random.nextInt());
        System.out.println(speedHistoryDAO.insert(speedHistoryDO, userDTO));
    }
}
