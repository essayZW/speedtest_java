package cn.imessay.speedtest.pojo.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDTO {
    private Integer id;
    private String username;
    private JSONObject extraAttribute;
}
