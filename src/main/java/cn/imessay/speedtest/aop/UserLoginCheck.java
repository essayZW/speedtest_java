package cn.imessay.speedtest.aop;

import cn.imessay.speedtest.annoation.UserLogin;
import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import cn.imessay.speedtest.service.redis.RedisService;
import cn.imessay.speedtest.service.user.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@Component
@Aspect
@Order(Integer.MIN_VALUE + 3)
public class UserLoginCheck {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Pointcut("@annotation(cn.imessay.speedtest.annoation.UserLogin)")
    private void check() {}

    @Around("check() && @annotation(userLogin)")
    private Object around(ProceedingJoinPoint joinPoint, UserLogin userLogin) throws Throwable {
        Object[] args = joinPoint.getArgs();
        ModelAndView model = null;
        for (Object arg : args) {
            if (arg instanceof ModelAndView) {
                model = (ModelAndView) arg;
                break;
            }
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserDTO userDTO = getLoginUserInfo(request);
        if (userLogin.redirect() && userDTO == null) {
            return "redirect:" + GlobalConfig.NOT_LOGIN_REDIRECT_URL;
        }
        model.getModel().put(GlobalConfig.MODEL_USER_KEY, userDTO);
        return joinPoint.proceed();
    }

    private UserDTO getLoginUserInfo(HttpServletRequest request) {
        String sessionId = getSessionId(request);
        if (sessionId == null) {
            return null;
        }
        Object value = redisService.get(GlobalConfig.USER_SESSION_KEY_PREFIX + sessionId);
        Integer userId = null;
        try {
            userId = Integer.valueOf(value.toString());
        }
        catch (Exception e) {
            return null;
        }
        return userService.getUserInfo(userId);
    }

    public static String getSessionId(HttpServletRequest request) {
        return getSessionId(request, GlobalConfig.SESSION_ID_NAME);
    }
    public static String getSessionId(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), key)) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
