package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.annoation.AdminPermission;
import cn.imessay.speedtest.config.ErrorCode;
import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.admin.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @PostMapping
    public BaseResponseBody<Object> login(@RequestParam String password,
                                          @RequestParam(required = false, defaultValue = "false") Boolean setCookie,
                                          HttpServletResponse response) {
        String sessionId = adminService.login(password);
        if (sessionId == null) {
            return BaseResponseBody.error(ErrorCode.USER_LOGIN_FAIL);
        }
        logger.info("Admin Login||{}", sessionId);
        if (setCookie) {
            Cookie cookie = new Cookie(GlobalConfig.ADMIN_SESSION_NAME, sessionId);
            cookie.setMaxAge(GlobalConfig.USER_SESSION_EXPIRE_SECONDS.intValue());
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("sessionId", sessionId);
        return BaseResponseBody.ok(responseData);
    }

    @DeleteMapping
    @AdminPermission
    public BaseResponseBody<Object> logout() {
        if (adminService.logout()) {
            return BaseResponseBody.ok("success");
        }
        else {
            return BaseResponseBody.error(ErrorCode.USER_LOGOUT_FAIL);
        }
    }
}
