package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.config.ErrorCode;
import cn.imessay.speedtest.exception.ParamValidException;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import cn.imessay.speedtest.pojo.vo.UserVO;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public BaseResponseBody<Object> getUserInfo(Integer id) throws ParamValidException {
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
}
