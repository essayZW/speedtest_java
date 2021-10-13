package cn.imessay.speedtest.dao.point;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TestPointDO {
    private int id;
    private String name;
    private String server;
    private short port;
    private short status;
}