package cn.imessay.mybatis.type.handler;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

class JsonTypeHandlerTest {


    @Test
    public void test() {
        JSONObject a = JSONObject.parseObject("");
        JSONObject b = JSONObject.parseObject(null);
        JSONObject c = JSONObject.parseObject("xss");
    }
}
