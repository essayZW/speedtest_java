package cn.imessay.speedtest.service.ip;

import cn.imessay.speedtest.dao.cidr.CidrDO;
import cn.imessay.speedtest.pojo.dto.IpInfoDTO;
import cn.imessay.speedtest.service.cidr.CidrService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class IpService {
    private Logger logger = LoggerFactory.getLogger(IpService.class);

    @Autowired
    private CidrService cidrService;

    public IpInfoDTO getInfo(String ip) {
        CidrDO cidrDO = cidrService.getFirstMatched(ip);
        IpInfoDTO ipInfoDTO = new IpInfoDTO();
        ipInfoDTO.setIp(ip);
        if (cidrDO == null) {
            try {
                return queryIpInfo(ip);
            }
            catch (InterruptedException e) {
                return ipInfoDTO;
            }
        }
        ipInfoDTO.setCidr(cidrDO.getCidr());
        ipInfoDTO.setIsp(cidrDO.getIsp());
        ipInfoDTO.setPosition(cidrDO.getPosition());
        ipInfoDTO.setAccessMethod(cidrDO.getAccessMethod());
        return ipInfoDTO;
    }

    private IpInfoDTO queryIpInfo(String ip) throws InterruptedException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://ip-api.com/json/" + ip).build();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        JSONObject responseJson = new JSONObject();
        responseJson.put("isp", null);
        responseJson.put("position", null);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                logger.debug("Ip Api Query Fail||{}||{}||{}", ip, e.getMessage(), e.getClass().getName());
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                JSONObject res = JSONObject.parseObject(response.body().string());
                // 检测是否查询失败
                if (Objects.equals("fail", res.getString("status"))) {
                    logger.debug("Ip Api Query Fail||{}||{}", ip, response.body().string());
                    countDownLatch.countDown();
                    return;
                }
                if (res.containsKey("isp")) {
                    responseJson.put("isp", res.getString("isp"));
                }
                StringBuilder builder = new StringBuilder();
                if (res.containsKey("country")) {
                    builder.append(res.getString("country")).append(" ");
                }
                if (res.containsKey("regionName")) {
                    builder.append(res.getString("regionName")).append(" ");
                }
                if (res.containsKey("city")) {
                    builder.append(res.getString("city"));
                }
                responseJson.put("position", builder.toString());
                countDownLatch.countDown();
            }
        });
        countDownLatch.await(3, TimeUnit.SECONDS);

        IpInfoDTO ipInfoDTO = new IpInfoDTO();
        ipInfoDTO.setIp(ip);
        ipInfoDTO.setPosition(responseJson.getString("position"));
        ipInfoDTO.setIsp(responseJson.getString("isp"));
        return ipInfoDTO;
    }
}
