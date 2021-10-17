package cn.imessay.speedtest.mapper;

import cn.imessay.speedtest.dao.point.TestPointDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestPointMapperTest {

    @Autowired
    private TestPointMapper testPointMapper;

    @Test
    void queryAll() {
        List<TestPointDO> testPointDOList = testPointMapper.queryAll(1);
        for (TestPointDO testPointDO : testPointDOList) {
            System.out.println(testPointDO.toString());
        }
        testPointDOList = testPointMapper.queryAll(null);
        for (TestPointDO testPointDO : testPointDOList) {
            System.out.println(testPointDO.toString());
        }
    }
}
