package cn.imessay.speedtest.service.cidr;


import cn.imessay.speedtest.dao.cidr.CidrDAO;
import cn.imessay.speedtest.dao.cidr.CidrDO;
import edazdarevic.commons.net.CIDRUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CidrService {
    @Autowired
    private CidrDAO cidrDAO;

    public CidrDO getSingle(int id) {
        return cidrDAO.query(id);
    }

    public List<CidrDO> get(int page, int pageSize, boolean order) {
        if (page == -1) {
            return cidrDAO.queryAll(order);
        }
        return cidrDAO.pageQuery((page - 1) * pageSize, pageSize, order);
    }

    public CidrDO getFirstMatched(String ip) {
        List<CidrDO> cidrDOList = matchAll(ip, get(-1, -1, true), false);
        if (cidrDOList.size() == 0) {
            return null;
        }
        return cidrDOList.get(0);
    }

    public List<CidrDO> getMatched(String ip) {
        return matchAll(ip, get(-1, -1, true), true);
    }

    private List<CidrDO> matchAll(String ip, List<CidrDO> cidrDOList, boolean mulMatch) {
        List<CidrDO> res = new ArrayList<>();
        for (CidrDO cidrDO : cidrDOList) {
            try {
                CIDRUtils cidrUtils = new CIDRUtils(cidrDO.getCidr());
                if (cidrUtils.isInRange(ip)) {
                    res.add(cidrDO);
                    if (!mulMatch) {
                        break;
                    }
                }
            }
            catch (UnknownHostException ignored) {
            }
        }
        return res;
    }
}
