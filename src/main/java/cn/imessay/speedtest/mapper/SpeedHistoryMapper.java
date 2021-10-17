package cn.imessay.speedtest.mapper;

import cn.imessay.speedtest.dao.history.SpeedHistoryDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpeedHistoryMapper {

    Integer insert(SpeedHistoryDO speedHistoryDO);
}
