package cn.imessay.speedtest.config;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    USER_NOT_FOUND("该用户不存在"),

    USER_ADD_FAIL("用户添加失败"),

    ADD_SPEED_HISTORY_FAIL("记录测速历史失败")
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
