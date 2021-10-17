package cn.imessay.speedtest.dao.cidr;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidrDO {

    private Integer id;
    private String cidr;
    private String position;
    private String accessMethod;
    private String isp;
    private Integer index;
}
