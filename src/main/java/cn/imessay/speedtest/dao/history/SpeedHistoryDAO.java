package cn.imessay.speedtest.dao.history;

import cn.imessay.speedtest.mapper.SpeedHistoryMapper;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpeedHistoryDAO {
    @Autowired
    private SpeedHistoryMapper speedHistoryMapper;

    public Long insert(SpeedHistoryDO speedHistoryDO, UserDTO userDTO) {
        speedHistoryDO.setUserid(userDTO.getId());
        Integer status = speedHistoryMapper.insert(speedHistoryDO);
        if (status == 0) {
            return null;
        }
        else {
            return speedHistoryDO.getId();
        }
    }
}
