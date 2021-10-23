package cn.imessay.speedtest.mapper;

import cn.imessay.speedtest.dao.cidr.CidrDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CidrMapperTest {

    @Autowired
    private CidrMapper cidrMapper;

    @Test
    void select() {
        List<CidrDO> cidrDOList = cidrMapper.query(-1, 0, 3, true);
        for (CidrDO cidrDO : cidrDOList) {
            System.out.println(cidrDO.getCidr());
        }
    }
}
