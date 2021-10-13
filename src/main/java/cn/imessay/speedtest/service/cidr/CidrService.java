package cn.imessay.speedtest.service.cidr;


import cn.imessay.speedtest.dao.cidr.CidrDAO;
import cn.imessay.speedtest.dao.cidr.CidrDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidrService {
    @Autowired
    private CidrDAO cidrDAO;

    public CidrDO getSingle(int id) {
        return cidrDAO.query(id);
    }

    public List<CidrDO> get(int page, int pageSize, boolean order) {
        return cidrDAO.pageQuery((page - 1) * pageSize, pageSize, order);
    }

    public CidrDO getFirstMatched(String ip) {
        return null;
    }

    public List<CidrDO> getMatched(String ip) {
        return null;
    }
}
