package cn.imessay.speedtest.aop;

import cn.imessay.speedtest.response.BaseResponseBody;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;


@Aspect
@Component
public class ApiResponseHandler {

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
            ExceptionData exceptionData = new ExceptionData();
            exceptionData.setMessage(e.getMessage());
            responseData = BaseResponseBody.error(exceptionData);
            ((BaseResponseBody) responseData).setCode(500);
        }
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(((BaseResponseBody) responseData).getCode());
        return responseData;
    }

    private class ExceptionData {
        private String message;

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
