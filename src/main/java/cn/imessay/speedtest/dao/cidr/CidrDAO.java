package cn.imessay.speedtest.dao.cidr;

import cn.imessay.speedtest.mapper.CidrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CidrDAO {

    @Autowired
    private CidrMapper cidrMapper;

    public List<CidrDO> queryAll(boolean order) {
        return cidrMapper.query(-1, -1, -1, order);
    }

    public List<CidrDO> pageQuery(int offset, int size, boolean order) {
        return cidrMapper.query(-1, offset, size, order);
    }

    public CidrDO query(int id) {
        List<CidrDO> cidrDOList = cidrMapper.query(id, -1, -1, false);
        if (cidrDOList.size() == 0) {
            return null;
        }
        return cidrDOList.get(0);
    }
}
