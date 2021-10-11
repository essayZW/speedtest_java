package cn.imessay.speedtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 该控制器类主要提供测速相关的接口
 */
@Controller
@RequestMapping("/speed/api")
public class TestApiController {

    /**
     * 该接口用于测试下载速度
     * 因此需要生成一个随机的文件并返回
     */
    @RequestMapping("/download")
    public void download() {

    }


    /**
     * 该接口用于测试上传速度以及延迟
     * 因此不需要返回任何内容
     * @return String 内容为空的空内容
     */
    @RequestMapping("/empty")
    @ResponseBody
    public String empty() {
        return "";
    }

    /**
     * 该接口用于获取客户端的IP信息
     */
    @RequestMapping("/ip")
    @ResponseBody
    public void getIp() {

    }


}
