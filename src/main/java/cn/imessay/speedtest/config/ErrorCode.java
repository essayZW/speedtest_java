package cn.imessay.speedtest.config;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    USER_NOT_FOUND("该用户不存在"),

    USER_ADD_FAIL("用户添加失败"),

    USER_REPEAT_LOGIN("用户已经登录"),

    USER_LOGIN_FAIL("用户登录失败"),

    USER_NOT_LOGIN("用户还未登陆"),

    USER_LOGOUT_FAIL("用户退出登录失败"),

    REGISTER_FORBIDDEN("用户注册受限制"),

    ADD_SPEED_HISTORY_FAIL("记录测速历史失败"),

    INVALID_CONFIG_NAME("不存在的配置名称"),

    CONFIG_UPDATE_FAIL("配置修改失败"),

    PERMISSION_FORBIDDEN("操作权限受限制")
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
