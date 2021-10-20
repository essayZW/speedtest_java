package cn.imessay.speedtest.mapper;

import cn.imessay.speedtest.dao.user.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDO query(Integer id);
    Integer insert(UserDO userDO);
    Integer queryId(String username);
    UserDO loginQuery(String username, String password);
}
