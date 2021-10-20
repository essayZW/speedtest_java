package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.annoation.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("")
    public String index() {
        return "index";
    }


    @RequestMapping("/history")
    @UserLogin(redirect = true)
    public String history(ModelAndView modelAndView) {
        return "index";
    }
}
