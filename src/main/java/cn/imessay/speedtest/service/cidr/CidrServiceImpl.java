package cn.imessay.speedtest.service.cidr;

import cn.imessay.speedtest.dao.cidr.CidrDO;
import cn.imessay.speedtest.manager.cidr.CidrManager;
import cn.imessay.speedtest.pojo.dto.IpInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidrServiceImpl implements CidrService {

    @Autowired
    private CidrManager cidrManager;

    @Override
    public CidrDO getSingle(int id) {
        return null;
    }

    @Override
    public List<CidrDO> get(int page, int pageSize, boolean order) {
        return cidrManager.get(page, pageSize, order);
    }

    @Override
    public CidrDO getFirstMatched(String ip) {
        return cidrManager.getFirstMatched(ip);
    }

    @Override
    public List<CidrDO> getMatched(String ip) {
        return cidrManager.getMatched(ip);
    }
}
