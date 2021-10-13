package cn.imessay.speedtest.mybatis.type.handler;

import cn.imessay.speedtest.mybatis.type.AddressType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.TINYINT)
public class AddressTypeHandler extends BaseTypeHandler<AddressType> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, AddressType addressType, JdbcType jdbcType) throws SQLException {
        if (addressType == AddressType.IPv6) {
            preparedStatement.setInt(i, 6);
        }
        else {
            preparedStatement.setInt(i, 4);
        }
    }

    @Override
    public AddressType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int type = resultSet.getInt(s);
        if (type == 6) {
            return AddressType.IPv6;
        }
        else {
            return AddressType.IPv4;
        }
    }

    @Override
    public AddressType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int type = resultSet.getInt(i);
        if (type == 6) {
            return AddressType.IPv6;
        }
        else {
            return AddressType.IPv4;
        }
    }

    @Override
    public AddressType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int type = callableStatement.getInt(i);
        if (type == 6) {
            return AddressType.IPv6;
        }
        else {
            return AddressType.IPv4;
        }
    }

}
