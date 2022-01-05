package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.annoation.NoCache;
import cn.imessay.speedtest.pojo.dto.IpInfoDTO;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.ip.IpService;
import cn.imessay.speedtest.util.HttpRequestIP;
import cn.imessay.speedtest.util.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 该控制器类主要提供测速相关的接口
 */
@Controller
@RequestMapping(value = "/speed/api", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
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
    @RequestMapping("/download")
    @CrossOrigin
    public void download(@RequestParam(defaultValue = "4") Integer ckSize,
                         HttpServletResponse response) throws IOException {

        addDownloadHeader(response);
        logger.info("Call download api, download {}MB file",
                ckSize);
        final int maxCkSize = 2048;
        if (ckSize > maxCkSize) {
            ckSize = maxCkSize;
        }
        PrintWriter printWriter = response.getWriter();
        String str = RandomString.generate(1048576);
        while ((ckSize--) > 0) {
            printWriter.write(str);
        }
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
    @CrossOrigin
    public String empty(HttpServletRequest request) {
        int bodySize = request.getContentLength();
        try {
            // read all body data, to avoid net::connection_reset_error on client
            ServletInputStream stream = request.getInputStream();
            for (int i = 0; i < bodySize; i ++) {
                stream.read();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (bodySize > 0) {
            logger.info("Call empty api, upload file size {}MB", bodySize / 1048576);
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
    @CrossOrigin
    public BaseResponseBody<IpInfoDTO> getIp(HttpServletRequest request) {
        String ip = HttpRequestIP.get(request);
        IpInfoDTO ipInfoDTO = ipService.getInfo(ip);
        return BaseResponseBody.ok(ipInfoDTO);
    }


}
