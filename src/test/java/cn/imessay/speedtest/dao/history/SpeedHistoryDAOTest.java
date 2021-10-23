package cn.imessay.speedtest.dao.history;

import cn.imessay.speedtest.pojo.dto.SpeedHistoryDTO;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import cn.imessay.speedtest.pojo.vo.PageQueryVO;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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

    @Test
    void queryByUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        PageQueryVO pageQueryVO = new PageQueryVO();
        pageQueryVO.setIndex(2);
        pageQueryVO.setSize(3);
        List<SpeedHistoryDTO> speedHistoryDTOS = speedHistoryDAO.queryByUser(userDTO, pageQueryVO);
        for (SpeedHistoryDTO speedHistoryDTO : speedHistoryDTOS) {
            System.out.println(speedHistoryDTO);
        }
    }
}
