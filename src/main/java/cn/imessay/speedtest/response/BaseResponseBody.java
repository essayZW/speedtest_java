package cn.imessay.speedtest.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseBody<T> {
    private Boolean status;
    private T data;
    private Integer code = 200;
    private String className;


    public BaseResponseBody() {}
    public BaseResponseBody(T data, Boolean status) {
        this.data = data;
        this.status = status;
        this.className = data.getClass().getName();
    }

    public static <T> BaseResponseBody<T> ok(T data) {
        BaseResponseBody<T> res =  new BaseResponseBody<>();
        res.setStatus(true);
        res.setData(data);
        res.setClassName(data.getClass().getName());
        return res;
    }

    public static <T> BaseResponseBody<T> error(T data) {
        BaseResponseBody<T> res =  new BaseResponseBody<>();
        res.setStatus(false);
        res.setData(data);
        res.setClassName(data.getClass().getName());
        return res;
    }
}
