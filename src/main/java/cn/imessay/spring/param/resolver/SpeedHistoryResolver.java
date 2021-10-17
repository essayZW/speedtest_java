package cn.imessay.spring.param.resolver;

import cn.imessay.speedtest.exception.ParamValidException;
import cn.imessay.speedtest.pojo.vo.SpeedHistoryVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SpeedHistoryResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(SpeedHistoryVO.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        SpeedHistoryVO speedHistoryVO = new SpeedHistoryVO();
        String ip = webRequest.getParameter("ip");
        if (ip == null || ip.isEmpty()) {
            throw new ParamValidException("ip信息不能为空");
        }
        speedHistoryVO.setIp(ip);
        String ua = webRequest.getParameter("ua");
        if (ua == null || ua.isEmpty()) {
            throw new ParamValidException("UserAgent 信息不能为空");
        }
        speedHistoryVO.setUa(ua);
        try {
            speedHistoryVO.setDl(Float.valueOf(webRequest.getParameter("dl")));
            speedHistoryVO.setUl(Float.valueOf(webRequest.getParameter("ul")));
            speedHistoryVO.setPing(Float.valueOf(webRequest.getParameter("ping")));
            speedHistoryVO.setJitter(Float.valueOf(webRequest.getParameter("jitter")));
            speedHistoryVO.setTestPointId(Integer.valueOf(webRequest.getParameter("testPointId")));
        }
        catch (Exception e) {
            throw new ParamValidException("缺少参数");
        }
        speedHistoryVO.setExtraAttribute(JSONObject.parseObject(webRequest.getParameter("extraAttribute")));
        return speedHistoryVO;
    }
}
