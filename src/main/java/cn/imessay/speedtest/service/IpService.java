package cn.imessay.speedtest.service;

import cn.imessay.speedtest.dto.IpInfoDTO;
import org.springframework.stereotype.Service;

@Service
public class IpService {

    public IpInfoDTO getInfo(String ip) {
        IpInfoDTO ipInfoDTO = new IpInfoDTO();
        ipInfoDTO.setIp(ip);
        return ipInfoDTO;
    }
}
