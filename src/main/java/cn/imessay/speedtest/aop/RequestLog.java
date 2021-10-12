package cn.imessay.speedtest.aop;

import cn.imessay.speedtest.util.HttpRequestIP;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@Order(0)
public class RequestLog {
    private final Logger logger = LoggerFactory.getLogger(RequestLog.class);

    private ThreadLocal<Long> startTimeLocal = new ThreadLocal<>();

    @Pointcut("execution(* cn.imessay.speedtest.controller..*.*(..))")
    private void writeLog() {}

    @Before("writeLog()")
    public void before() {
        Date date = new Date();
        startTimeLocal.set(date.getTime());
    }

    @After("writeLog()")
    public void after() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Date date = new Date();
        logger.info("{}||{}||{}||{}||requestSize:{} start:{} end:{} process:{}ms",
                HttpRequestIP.get(request),
                request.getMethod(),
                request.getRequestURI(),
                request.getHeader("User-Agent"),
                request.getContentLength(),
                startTimeLocal.get(),
                date.getTime(),
                date.getTime() - startTimeLocal.get());
    }
}
