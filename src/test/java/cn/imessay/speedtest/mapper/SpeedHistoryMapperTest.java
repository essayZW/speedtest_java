package cn.imessay.speedtest.mapper;

import cn.imessay.speedtest.dao.history.SpeedHistoryDO;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpeedHistoryMapperTest {

    @Autowired
    private SpeedHistoryMapper speedHistoryMapper;

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
        speedHistoryDO.setUserid(1);
        speedHistoryDO.setTestPointId(1);
        speedHistoryDO.setExtraAttribute(JSONObject.parseObject("{\"a\":1}"));
        System.out.println(speedHistoryMapper.insert(speedHistoryDO));
    }
}
