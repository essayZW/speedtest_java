package cn.imessay.speedtest.dao.cidr;

import cn.imessay.speedtest.mapper.CidrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CidrDAO {

    @Autowired
    private CidrMapper cidrMapper;

    public List<CidrDO> selectAll(boolean order) {
        return cidrMapper.select(-1, -1, -1, order);
    }

    public List<CidrDO> pageSelect(int offset, int size, boolean order) {
        return cidrMapper.select(-1, offset, size, order);
    }
}
