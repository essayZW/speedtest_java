package cn.imessay.speedtest.mapper;

import cn.imessay.speedtest.dao.history.SpeedHistoryDO;
import cn.imessay.speedtest.pojo.dto.SpeedHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpeedHistoryMapper {

    Integer insert(SpeedHistoryDO speedHistoryDO);
    List<SpeedHistoryDTO> queryByUserId(Integer userId, Integer offset, Integer limit);
    Integer queryCountByUser(Integer userId);
}
