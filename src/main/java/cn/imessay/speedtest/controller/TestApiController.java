package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.annoation.AllowCors;
import cn.imessay.speedtest.annoation.NoCache;
import cn.imessay.speedtest.dto.IpInfoDTO;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.IpService;
import cn.imessay.speedtest.util.HttpRequestIP;
import cn.imessay.speedtest.util.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 该控制器类主要提供测速相关的接口
 */
@Controller
@RequestMapping("/speed/api")
public class TestApiController {
    private final Logger logger = LoggerFactory.getLogger(TestApiController.class);


    @Autowired
    private IpService ipService;

    /**
     * 该接口用于测试下载速度
     * 因此需要生成一个随机的文件并返回
     * 返回指定长度的字符串即可
     */
    @NoCache
    @AllowCors
    @RequestMapping("/download")
    public void download(@RequestParam(defaultValue = "4") Integer ckSize,
                         HttpServletResponse response) throws IOException {

        addDownloadHeader(response);
        logger.info("Call download api, download {}MB file",
                ckSize);
        final int maxCkSize = 2048;
        if (ckSize > maxCkSize) {
            ckSize = maxCkSize;
        }
        OutputStream stream;
        stream = response.getOutputStream();
        while ((ckSize--) > 0) {
            stream.write(RandomString.generateByte(1048576));
        }
        stream.flush();
        stream.close();
    }


    /**
     * 添加下载文件的响应头
     */
    private void addDownloadHeader(HttpServletResponse response) {
        response.setHeader("Content-Description", "File Transfer");
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=random.dat");
        response.setHeader("Content-Transfer-Encoding", "binary");
    }


    /**
     * 该接口用于测试上传速度以及延迟
     * 因此不需要返回任何内容
     * @return String 内容为空的空内容
     */
    @RequestMapping("/empty")
    @ResponseBody
    @NoCache
    @AllowCors
    public String empty(HttpServletRequest request) {
        int bodySize = request.getContentLength() / 1048576;
        if (bodySize > 0) {
            logger.info("Call empty api, upload file size {}MB", bodySize);
        }
        else {
            logger.info("Call empty api, Ping!");
        }
        return "";
    }

    /**
     * 该接口用于获取客户端的IP信息
     */
    @RequestMapping("/ip")
    @ResponseBody
    @NoCache
    @AllowCors
    public BaseResponseBody getIp(HttpServletRequest request) {
        String ip = HttpRequestIP.get(request);
        IpInfoDTO ipInfoDTO = ipService.getInfo(ip);
        return BaseResponseBody.ok(ipInfoDTO);
    }


}
