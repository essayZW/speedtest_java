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

    public Integer insert(UserDO userDO) {
        if (userDO == null) return null;
        int status = userMapper.insert(userDO);
        if (status == 0) {
            return null;
        }
        else {
            return userDO.getId();
        }
    }

    public Integer queryId(String username) {
        return userMapper.queryId(username);
    }
}
