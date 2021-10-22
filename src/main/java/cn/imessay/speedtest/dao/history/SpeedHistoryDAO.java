package cn.imessay.speedtest.dao.history;

import cn.imessay.speedtest.mapper.SpeedHistoryMapper;
import cn.imessay.speedtest.pojo.dto.SpeedHistoryDTO;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import cn.imessay.speedtest.pojo.vo.PageQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<SpeedHistoryDTO> queryByUser(UserDTO userDTO, PageQueryVO pageQueryVO) {
        int offset = (pageQueryVO.getIndex() - 1) * pageQueryVO.getSize();
        return speedHistoryMapper.queryByUserId(userDTO.getId(), offset, pageQueryVO.getSize());
    }

    public Integer queryCountByUser(UserDTO userDTO) {
        return speedHistoryMapper.queryCountByUser(userDTO.getId());
    }
}
