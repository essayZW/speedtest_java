package cn.imessay.speedtest.mapper;

import cn.imessay.speedtest.dao.point.TestPointDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestPointMapper {
    List<TestPointDO> queryAll(Integer status);
}
