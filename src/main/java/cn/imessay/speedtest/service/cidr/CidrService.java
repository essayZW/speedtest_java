package cn.imessay.speedtest.service.cidr;


import cn.imessay.speedtest.dao.cidr.CidrDO;
import cn.imessay.speedtest.pojo.dto.IpInfoDTO;

import java.util.List;

public interface CidrService {
    List<CidrDO> get(int page, int pageSize, boolean order);
    CidrDO getSingle(int id);

    List<CidrDO> getMatched(String ip);
    CidrDO getFirstMatched(String ip);
}
