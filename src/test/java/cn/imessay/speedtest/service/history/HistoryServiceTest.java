package cn.imessay.speedtest.service.history;

import cn.imessay.speedtest.exception.UserNotFoundException;
import cn.imessay.speedtest.pojo.vo.SpeedHistoryVO;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HistoryServiceTest {
    @Autowired
    private HistoryService historyService;

    @Test
    void add() throws UserNotFoundException {
        SpeedHistoryVO speedHistoryVO = new SpeedHistoryVO();
        Random random = new Random();
        speedHistoryVO.setIp("127.0.0.1");
        speedHistoryVO.setUa("Useragent");
        speedHistoryVO.setDl(random.nextFloat());
        speedHistoryVO.setUl(random.nextFloat());
        speedHistoryVO.setPing(random.nextFloat());
        speedHistoryVO.setJitter(random.nextFloat());
        speedHistoryVO.setTestPointId(1);
        speedHistoryVO.setExtraAttribute(JSONObject.parseObject("{\"a\":1}"));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(random.nextInt());
        System.out.println(historyService.add(speedHistoryVO, userDTO));
        assertThrows(UserNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                historyService.add(speedHistoryVO, null);
            }
        });
        assertNull(historyService.add(null, userDTO));
    }
}
