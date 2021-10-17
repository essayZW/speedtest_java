package cn.imessay.speedtest.pojo.vo;

import cn.imessay.mybatis.type.AddressType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class IpInfoVO {

    private String ip;
    private String cidr;
    private String position;
    private String accessMethod;
    private String isp;
    private AddressType type;
}
