package cn.imessay.speedtest.aop;

import cn.imessay.speedtest.annoation.UserLogin;
import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.dao.user.UserDO;
import cn.imessay.speedtest.pojo.vo.UserVO;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.user.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@Component
@Aspect
public class UserLoginCheck {
    @Autowired
    private UserService userService;

    @Pointcut("@annotation(cn.imessay.speedtest.annoation.UserLogin)")
    private void check() {}

    @Around("check() && @annotation(userLogin)")
    private Object around(ProceedingJoinPoint joinPoint, UserLogin userLogin) {
        Object[] args = joinPoint.getArgs();
        ModelAndView model = null;
        for (Object arg : args) {
            if (arg instanceof ModelAndView) {
                model = (ModelAndView) arg;
                break;
            }
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserVO userVO = getLoginUserInfo(request);
        if (userLogin.redirect() && userVO == null) {
            return "redirect:" + GlobalConfig.NOT_LOGIN_REDIRECT_URL;
        }
        model.getModel().put(GlobalConfig.MODEL_USER_KEY, userVO);
        try {
            return joinPoint.proceed();
        } catch (Throwable ignored) {
            return null;
        }
    }

    private UserVO getLoginUserInfo(HttpServletRequest request) {
        String sessionId = getSessionId(request);
        if (sessionId == null) {
            return null;
        }
        return userService.getUserInfo(1);
    }

    private String getSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), GlobalConfig.SESSION_ID_NAME)) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
