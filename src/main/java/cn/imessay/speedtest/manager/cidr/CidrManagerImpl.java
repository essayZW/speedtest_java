package cn.imessay.speedtest.manager.cidr;

import cn.imessay.speedtest.dao.cidr.CidrDAO;
import cn.imessay.speedtest.dao.cidr.CidrDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CidrManagerImpl implements CidrManager {

    @Autowired
    private CidrDAO cidrDAO;

    @Override
    public List<CidrDO> get(int page, int pageSize, boolean order) {
        if (page == -1) {
            return cidrDAO.selectAll(order);
        }
        else {
            return cidrDAO.pageSelect((page - 1) * pageSize, pageSize, order);
        }
    }

    @Override
    public CidrDO getSingle(int id) {
        return null;
    }

    @Override
    public List<CidrDO> getMatched(String ip) {
        return getMatched(ip, false);
    }

    @Override
    public CidrDO getFirstMatched(String ip) {
        return getMatched(ip, true).get(0);
    }


    private List<CidrDO> getMatched(String ip, boolean single) {
        List<CidrDO> allCidr = get(-1, -1, true);
        for (CidrDO cidrDO : allCidr) {

        }
        return allCidr;
    }
}
