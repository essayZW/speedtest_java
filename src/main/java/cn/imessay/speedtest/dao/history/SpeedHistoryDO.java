package cn.imessay.speedtest.dao.history;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SpeedHistoryDO {
    Long id;
    Date time;
    String ip;
    String ua;
    Float dl;
    Float ul;
    Float ping;
    Float jitter;
    Integer userid;
    Integer testPointId;
    JSONObject extraAttribute;
}
