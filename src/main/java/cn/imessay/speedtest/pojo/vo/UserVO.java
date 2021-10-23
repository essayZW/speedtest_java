package cn.imessay.speedtest.pojo.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserVO {

    @NotNull(message = "缺少用户名参数")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 6, max = 16, message = "用户名长度必须在6~16之间")
    private String username;

    @NotNull(message = "缺少密码参数")
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度必须在6~16之间")
    private String password;

    private String extraAttribute;
}
