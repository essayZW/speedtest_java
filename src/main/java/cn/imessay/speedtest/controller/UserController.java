package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.annoation.UserLogin;
import cn.imessay.speedtest.config.ErrorCode;
import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import cn.imessay.speedtest.pojo.vo.UserVO;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.user.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public BaseResponseBody<Object> getUserInfo(Integer id) {
        if (id == null) return BaseResponseBody.error(ErrorCode.USER_NOT_FOUND);
        UserDTO userDTO = userService.getUserInfo(id);
        if (userDTO == null) return BaseResponseBody.error(ErrorCode.USER_NOT_FOUND);
        return BaseResponseBody.ok(userDTO);
    }


    @PostMapping
    public BaseResponseBody<Object> addUser(@Validated UserVO userVO) {
        byte[] passwordBytes = userVO.getPassword().getBytes(StandardCharsets.UTF_8);
        String md5Password = DigestUtils.md5DigestAsHex(passwordBytes);
        userVO.setPassword(md5Password);
        Integer id = userService.addUser(userVO);
        if (id == null) {
            return BaseResponseBody.error(ErrorCode.USER_ADD_FAIL);
        }
        Map<String, Integer> responseData = new HashMap<>();
        responseData.put("id", id);
        return BaseResponseBody.ok(responseData);
    }

    @PostMapping("/session")
    @UserLogin
    public BaseResponseBody<Object> login(@RequestParam String username,
                                          @RequestParam String password,
                                          ModelAndView modelAndView) {
        UserDTO userDTO = (UserDTO) modelAndView.getModel().get(GlobalConfig.MODEL_USER_KEY);
        if (userDTO != null) {
            return BaseResponseBody.error(ErrorCode.USER_REPEAT_LOGIN);
        }
        UserDTO userInfo = new UserDTO();
        String sessionId = userService.login(username, password, userInfo);
        if (sessionId == null) {
            return BaseResponseBody.error(ErrorCode.USER_LOGIN_FAIL);
        }
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("sessionId", sessionId);
        responseData.put("info", userInfo);
        return BaseResponseBody.ok(responseData);
    }
}
