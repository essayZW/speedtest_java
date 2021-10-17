package cn.imessay.speedtest.dao.history;

import cn.imessay.speedtest.mapper.SpeedHistoryMapper;
import cn.imessay.speedtest.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpeedHistoryDAO {
    @Autowired
    private SpeedHistoryMapper speedHistoryMapper;

    public Long insert(SpeedHistoryDO speedHistoryDO, UserVO userVO) {
        speedHistoryDO.setUserid(userVO.getId());
        Integer status = speedHistoryMapper.insert(speedHistoryDO);
        if (status == 0) {
            return null;
        }
        else {
            return speedHistoryDO.getId();
        }
    }
}
