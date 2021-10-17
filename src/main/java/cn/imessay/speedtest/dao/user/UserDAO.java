package cn.imessay.speedtest.dao.user;

import cn.imessay.speedtest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private UserMapper userMapper;

    public UserDO query(int id) {
        return userMapper.query(id);
    }
}
