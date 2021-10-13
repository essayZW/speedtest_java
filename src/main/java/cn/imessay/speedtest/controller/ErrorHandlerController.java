package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.aop.ApiResponseHandler;
import cn.imessay.speedtest.response.BaseResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Controller
public class ErrorHandlerController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(ErrorHandlerController.class);

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseResponseBody<ApiResponseHandler.ExceptionData> errorJson() {
        ApiResponseHandler.ExceptionData exceptionData =  new ApiResponseHandler.ExceptionData();
        exceptionData.setMessage("404 not found");
        BaseResponseBody<ApiResponseHandler.ExceptionData> response = BaseResponseBody.error(exceptionData);
        response.setCode(404);
        return response;
    }

    @RequestMapping(value = "/error")
    public String errorPage() {
        return "404";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionPage(Exception e, HttpServletRequest request) {
        logger.debug("Exception:{}||{}||{}", e.getMessage(), e.getClass().getName(), request.getRequestURI());
        return "500";
    }

}
