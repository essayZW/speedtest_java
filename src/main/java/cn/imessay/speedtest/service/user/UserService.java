package cn.imessay.speedtest.service.user;

import cn.imessay.speedtest.dao.user.UserDAO;
import cn.imessay.speedtest.dao.user.UserDO;
import cn.imessay.speedtest.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserVO getUserInfo(int id) {
        UserDO userDO = userDAO.query(id);
        if (userDO == null) {
            return null;
        }

        UserVO userVO = new UserVO();
        userVO.setUsername(userDO.getUsername());
        userVO.setId(userDO.getId());
        userVO.setExtraAttribute(userDO.getExtraAttribute());
        return userVO;
    }

}
