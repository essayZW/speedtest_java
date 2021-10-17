package cn.imessay.speedtest.service.ip;

import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.dao.cidr.CidrDO;
import cn.imessay.mybatis.type.AddressType;
import cn.imessay.speedtest.pojo.dto.IpInfoDTO;
import cn.imessay.speedtest.service.cidr.CidrService;
import com.alibaba.fastjson.JSONObject;
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
        ipInfoDTO.setType(getType(ip));
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

    public AddressType getType(String ip) {
        // IPv6地址中一定有 : 符号
        if (ip.indexOf(":", 0) != -1) {
            return AddressType.IPv6;
        }
        else {
            return AddressType.IPv4;
        }
    }

    private IpInfoDTO queryIpInfo(String ip) throws InterruptedException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(GlobalConfig.IP_API_ADDRESS + ip).build();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        JSONObject responseJson = new JSONObject();
        responseJson.put("isp", null);
        responseJson.put("position", null);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                logger.warn("Ip Api Query Fail||{}||{}||{}", ip, e.getMessage(), e.getClass().getName());
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                JSONObject res = JSONObject.parseObject(response.body().string());
                // 检测是否查询失败
                if (Objects.equals("fail", res.getString("status"))) {
                    logger.warn("Ip Api Query Fail||{}||{}", ip, response.body().string());
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
        countDownLatch.await(GlobalConfig.MAX_IP_API_AWAIT_TIME, TimeUnit.SECONDS);

        IpInfoDTO ipInfoDTO = new IpInfoDTO();
        ipInfoDTO.setIp(ip);
        ipInfoDTO.setPosition(responseJson.getString("position"));
        ipInfoDTO.setIsp(responseJson.getString("isp"));
        return ipInfoDTO;
    }
}
