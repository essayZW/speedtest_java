package cn.imessay.mybatis.type.handler;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonTypeHandler extends BaseTypeHandler<JSONObject> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, JSONObject jsonObject, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, jsonObject.toJSONString());
    }

    @Override
    public JSONObject getNullableResult(ResultSet resultSet, String s) throws SQLException {
        try {
            return JSONObject.parseObject(resultSet.getString(s));
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public JSONObject getNullableResult(ResultSet resultSet, int i) throws SQLException {
        try {
            return JSONObject.parseObject(resultSet.getString(i));
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public JSONObject getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        try {
            return JSONObject.parseObject(callableStatement.getString(i));
        }
        catch (Exception e) {
            return null;
        }
    }
}
