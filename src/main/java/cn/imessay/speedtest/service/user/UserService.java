package cn.imessay.speedtest.service.user;

import cn.imessay.speedtest.dao.user.UserDAO;
import cn.imessay.speedtest.dao.user.UserDO;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import cn.imessay.speedtest.pojo.vo.UserVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserDTO getUserInfo(int id) {
        UserDO userDO = userDAO.query(id);
        if (userDO == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userDO.getUsername());
        userDTO.setId(userDO.getId());
        userDTO.setExtraAttribute(userDO.getExtraAttribute());
        return userDTO;
    }

    public Integer getUserIdByUsername(String username) {
        return userDAO.queryId(username);
    }

    public Integer addUser(UserVO userVO) {
        if (userVO == null) return null;

        // 将查询和插入合并成一个原子操作, 保证用户名的唯一性
        synchronized (userVO.getUsername().intern()) {
            Integer id = getUserIdByUsername(userVO.getUsername());
            if (id != null) return null;

            UserDO userDO = new UserDO();
            userDO.setUsername(userVO.getUsername());
            userDO.setExtraAttribute(JSONObject.parseObject(userVO.getExtraAttribute()));
            userDO.setPassword(userVO.getPassword());
            return userDAO.insert(userDO);
        }
    }
}
