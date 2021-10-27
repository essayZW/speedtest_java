package cn.imessay.speedtest.aop;

import cn.imessay.speedtest.annoation.AdminPermission;
import cn.imessay.speedtest.config.ErrorCode;
import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.redis.RedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
@Order(Integer.MIN_VALUE + 2)
public class AdminPermissionCheck {

    @Autowired
    private RedisService redisService;

    @Pointcut("@annotation(cn.imessay.speedtest.annoation.AdminPermission)")
    private void adminCheck() {}


    @Around("adminCheck() && @annotation(adminPermission)")
    public Object around(ProceedingJoinPoint joinPoint, AdminPermission adminPermission) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = UserLoginCheck.getSessionId(request, GlobalConfig.ADMIN_SESSION_NAME);
        if (sessionId == null) {
            return handler(adminPermission, ErrorCode.USER_NOT_LOGIN);
        }
        Object value = null;
        try {
            value = redisService.get(GlobalConfig.USER_SESSION_KEY_PREFIX + GlobalConfig.ADMIN_SESSION_KEY);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (value == null) {
            return handler(adminPermission, ErrorCode.PERMISSION_FORBIDDEN);
        }
        if (!Objects.equals(value.toString(), sessionId)) {
            return handler(adminPermission, ErrorCode.PERMISSION_FORBIDDEN);
        }
        return joinPoint.proceed();
    }

    private Object handler(AdminPermission adminPermission, Object responseData) {
        if (adminPermission.redirect()) {
            return "redirect:" + GlobalConfig.NOT_LOGIN_REDIRECT_URL;
        }
        else {
            return BaseResponseBody.error(responseData);
        }
    }
}
