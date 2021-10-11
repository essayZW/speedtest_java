package cn.imessay.speedtest.aop;

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

import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
@Order(1)
public class NoCache {
    private final Logger logger = LoggerFactory.getLogger(NoCache.class);

    @Pointcut("@annotation(cn.imessay.speedtest.annoation.NoCache)")
    private void addResponseHeader() {
    }

    @Before("addResponseHeader()")
    public void before() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (response == null) return;
        response.addHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, s-maxage=0");
        response.addHeader("Pragma", "no-cache");
    }
}
