package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.annoation.UserLogin;
import cn.imessay.speedtest.aop.UserLoginCheck;
import cn.imessay.speedtest.config.ErrorCode;
import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import cn.imessay.speedtest.pojo.vo.UserVO;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if (!GlobalConfig.ALLOW_USER_REGISTER) {
            return BaseResponseBody.error(ErrorCode.REGISTER_FORBIDDEN);
        }
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
                                          @RequestParam(required = false, defaultValue = "false") Boolean setCookie,
                                          ModelAndView modelAndView,
                                          HttpServletResponse response) {
        UserDTO userDTO = (UserDTO) modelAndView.getModel().get(GlobalConfig.MODEL_USER_KEY);
        if (userDTO != null) {
            return BaseResponseBody.error(ErrorCode.USER_REPEAT_LOGIN);
        }
        UserDTO userInfo = new UserDTO();
        String sessionId = userService.login(username, password, userInfo);
        if (sessionId == null) {
            return BaseResponseBody.error(ErrorCode.USER_LOGIN_FAIL);
        }
        if (setCookie) {
            Cookie cookie = new Cookie(GlobalConfig.SESSION_ID_NAME, sessionId);
            cookie.setMaxAge(GlobalConfig.USER_SESSION_EXPIRE_SECONDS.intValue());
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("sessionId", sessionId);
        responseData.put("info", userInfo);
        return BaseResponseBody.ok(responseData);
    }

    @DeleteMapping("/session")
    public BaseResponseBody<Object> logout(HttpServletRequest request) {
        String sessionId = UserLoginCheck.getSessionId(request);
        if (sessionId != null) {
            userService.logout(sessionId);
        }
        return BaseResponseBody.ok("success");
    }


    @GetMapping("/logined")
    @UserLogin
    public BaseResponseBody<Object> getLoginedInfo(ModelAndView modelAndView) {
        Object value = modelAndView.getModel().get(GlobalConfig.MODEL_USER_KEY);
        if (value != null) {
            return BaseResponseBody.ok(value);
        }
        else {
            return BaseResponseBody.error(ErrorCode.USER_NOT_LOGIN);
        }

    }
}
