package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.annoation.UserLogin;
import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.exception.CasLoginException;
import cn.imessay.speedtest.service.cas.CasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private CasService casService;

    @RequestMapping("")
    @UserLogin(redirect = true)
    public String index(ModelAndView modelAndView) {
        return "index";
    }


    @RequestMapping("/history")
    @UserLogin(redirect = true)
    public String history(ModelAndView modelAndView) {
        return "index";
    }


    @RequestMapping("/login")
    @UserLogin
    public String loginPage(ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        Object value = modelAndView.getModel().get(GlobalConfig.MODEL_USER_KEY);
        if (value != null) {
            return "redirect:/";
        }
        // CAS验证开启的情况下跳转到验证中心的登录界面
        if (GlobalConfig.ENABLE_CAS_LOGIN && !GlobalConfig.CAS_CENTER_ADDRESS.isEmpty()) {
            StringBuilder redirectUrl = new StringBuilder("redirect:");
            redirectUrl.append(GlobalConfig.CAS_CENTER_ADDRESS);
            redirectUrl.append("/login");
            redirectUrl.append("?service=");
            redirectUrl.append(getCasService(httpServletRequest));
            return redirectUrl.toString();
        }
        return "login";
    }
    private StringBuilder getCasService(HttpServletRequest request) {
        StringBuilder redirectUrl = new StringBuilder();
        redirectUrl.append("http://");
        redirectUrl.append(request.getServerName());
        redirectUrl.append(":");
        redirectUrl.append(request.getServerPort());
        redirectUrl.append("/login/ticket");
        return redirectUrl;
    }

    @RequestMapping("/register")
    @UserLogin
    public String registerPage(ModelAndView modelAndView) {
        Object value = modelAndView.getModel().get(GlobalConfig.MODEL_USER_KEY);
        if (value != null) {
            return "redirect:/";
        }
        return "register";
    }

    @RequestMapping("/login/ticket")
    public String casLogin(@RequestParam String ticket,
                           HttpServletRequest request,
                           HttpServletResponse response) throws InterruptedException, CasLoginException {
        String session = casService.login(ticket, getCasService(request).toString());
        if (session == null) {
            throw new CasLoginException("Cas login error");
        }
        Cookie cookie = new Cookie(GlobalConfig.SESSION_ID_NAME, session);
        cookie.setMaxAge(GlobalConfig.USER_SESSION_EXPIRE_SECONDS.intValue());
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }
}
