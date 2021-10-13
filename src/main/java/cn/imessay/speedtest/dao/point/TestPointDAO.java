package cn.imessay.speedtest.dao.point;

import cn.imessay.speedtest.mapper.TestPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestPointDAO {

    @Autowired
    private TestPointMapper testPointMapper;

    public List<TestPointDO> queryAvailable() {
        return testPointMapper.queryAll(1);
    }
}
