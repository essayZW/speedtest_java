package cn.imessay.speedtest.dao.point;

import cn.imessay.mybatis.type.AddressType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TestPointDO {
    private Integer id;
    private String name;
    private String server;
    private Short port;
    private Short status;
    private AddressType type;
}
