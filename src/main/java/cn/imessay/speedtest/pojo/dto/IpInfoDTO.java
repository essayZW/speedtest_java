package cn.imessay.speedtest.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class IpInfoDTO {

    private String ip;
    private String cidr;
    private String position;
    private String accessMethod;
    private String isp;
}
