package cn.imessay.speedtest.service.point;

import cn.imessay.speedtest.dao.point.TestPointDAO;
import cn.imessay.speedtest.dao.point.TestPointDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestPointService {
    @Autowired
    private TestPointDAO testPointDAO;

    public List<TestPointDO> queryAvailable() {
        return testPointDAO.queryAvailable();
    }
}
