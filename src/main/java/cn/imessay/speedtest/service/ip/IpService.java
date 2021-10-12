package cn.imessay.speedtest.service.ip;

import cn.imessay.speedtest.pojo.dto.IpInfoDTO;

public interface IpService {
    IpInfoDTO getInfo(String ip);
}
