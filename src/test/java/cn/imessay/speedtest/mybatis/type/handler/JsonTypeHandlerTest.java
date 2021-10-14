package cn.imessay.speedtest.mybatis.type.handler;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTypeHandlerTest {


    @Test
    public void test() {
        JSONObject a = JSONObject.parseObject("");
        JSONObject b = JSONObject.parseObject(null);
        JSONObject c = JSONObject.parseObject("xss");
    }
}
