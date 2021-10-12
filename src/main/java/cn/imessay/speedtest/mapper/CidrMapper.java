package cn.imessay.speedtest.mapper;

import cn.imessay.speedtest.dao.cidr.CidrDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CidrMapper {
    List<CidrDO> select(int id, int offset, int pageSize, boolean order);
}
