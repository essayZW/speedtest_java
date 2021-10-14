package cn.imessay.speedtest.aop;

import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.dao.user.UserDO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;


@Component
@Aspect
public class UserLoginCheck {

    @Pointcut("@annotation(cn.imessay.speedtest.annoation.UserLogin)")
    private void check() {}

    @Around("check()")
    private Object around(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ModelAndView model = null;
        for (Object arg : args) {
            if (arg instanceof ModelAndView) {
                model = (ModelAndView) arg;
                break;
            }
        }
        UserDO userDO = new UserDO();
        userDO.setId(1);
        model.getModel().put(GlobalConfig.MODEL_USER_KEY, userDO);
        try {
            return joinPoint.proceed();
        } catch (Throwable ignored) {
            return null;
        }
    }
}
