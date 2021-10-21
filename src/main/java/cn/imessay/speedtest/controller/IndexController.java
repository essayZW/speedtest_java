package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.annoation.UserLogin;
import cn.imessay.speedtest.config.GlobalConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

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
    public String loginPage(ModelAndView modelAndView) {
        Object value = modelAndView.getModel().get(GlobalConfig.MODEL_USER_KEY);
        if (value != null) {
            return "redirect:/";
        }
        return "login";
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
}
