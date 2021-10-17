package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.annoation.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("")
    public String index() {
        return "index";
    }


    @RequestMapping("/history")
    @UserLogin(redirect = true)
    public String history() {
        return "index";
    }
}
