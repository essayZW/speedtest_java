package cn.imessay.speedtest.pojo.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserVO {
    private Integer id;
    private String username;
    private JSONObject extraAttribute;
}
