package cn.imessay.speedtest.aop;

import cn.imessay.speedtest.response.BaseResponseBody;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Aspect
@Component
@Order(Integer.MIN_VALUE + 4)
public class ApiResponseHandler {

    private final Logger logger = LoggerFactory.getLogger(ApiResponseHandler.class);

    @Pointcut("execution(cn.imessay.speedtest.response.BaseResponseBody cn.imessay.speedtest.controller..*.*(..))")
    private void apiRes() {
    }


    @Around("apiRes()")
    public Object after(ProceedingJoinPoint joinPoint) {
        Object responseData;
        try {
            responseData = joinPoint.proceed();
        }
        catch (Throwable e) {
            e.printStackTrace();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            logger.warn("ApiException:{}||{}||{}", e.getMessage(), e.getClass().getName(), request.getRequestURI());
            ExceptionData exceptionData = new ExceptionData();
            exceptionData.setMessage(e.getMessage());
            responseData = BaseResponseBody.error(exceptionData);
            ((BaseResponseBody) responseData).setCode(500);
        }
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (response != null && responseData instanceof BaseResponseBody) {
            response.setStatus(((BaseResponseBody) responseData).getCode());
        }
        return responseData;
    }

    public static class ExceptionData {
        private String message;

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
