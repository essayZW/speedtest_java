package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.aop.ApiResponseHandler;
import cn.imessay.speedtest.response.BaseResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    public BaseResponseBody<Map<String, String>> exceptionPage(Exception e, HttpServletRequest request) throws Exception {
        logger.warn("Exception:{}||{}||{}", e.getMessage(), e.getClass().getName(), request.getRequestURI());
        if (e instanceof BindException) {
            BindException me = (BindException) e;
            List<ObjectError> errorList = me.getBindingResult().getAllErrors();
            Map<String, String> map = new HashMap<>();
            map.put("message", errorList.get(0).getDefaultMessage());
            map.put("error_count", String.valueOf(me.getErrorCount()));
            return BaseResponseBody.error(map);
        }
        throw e;
    }
}
