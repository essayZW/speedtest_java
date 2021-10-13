package cn.imessay.speedtest.response;


public class BaseResponseBody {
    private Boolean status;
    private Object data;
    private Integer code = 200;
    private String className;


    public BaseResponseBody() {}
    public BaseResponseBody(Object data, Boolean status) {
        this.data = data;
        this.status = status;
        this.className = data.getClass().getName();
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public static BaseResponseBody ok(Object data) {
        BaseResponseBody res =  new BaseResponseBody();
        res.setStatus(true);
        res.setData(data);
        res.setClassName(data.getClass().getName());
        return res;
    }

    public static BaseResponseBody error(Object data) {
        BaseResponseBody res =  new BaseResponseBody();
        res.setStatus(false);
        res.setData(data);
        res.setClassName(data.getClass().getName());
        return res;
    }
}
