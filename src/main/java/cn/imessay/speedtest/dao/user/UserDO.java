package cn.imessay.speedtest.dao.user;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDO {
    private Integer id;
    private String username;
    private String password;
    private JSONObject extraAttribute;
}
