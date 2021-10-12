package cn.imessay.speedtest.service.ip;

import cn.imessay.speedtest.pojo.dto.IpInfoDTO;
import cn.imessay.speedtest.service.cidr.CidrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpServiceImpl implements IpService {

    @Autowired
    private CidrService cidrService;

    public IpInfoDTO getInfo(String ip) {
        return null;
    }
}
