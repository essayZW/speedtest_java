package cn.imessay.speedtest.service.point;

import cn.imessay.speedtest.dao.point.TestPointDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestPointServiceTest {
    @Autowired
    private TestPointService testPointService;

    @Test
    void queryAvailable() {
        List<TestPointDO> testPointDOList = testPointService.queryAvailable();
        for (TestPointDO testPointDO : testPointDOList) {
            System.out.println(testPointDO.toString());
        }
    }
}
