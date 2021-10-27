package cn.imessay.speedtest.service.cas;

import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.pojo.vo.UserVO;
import cn.imessay.speedtest.service.user.UserService;
import okhttp3.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class CasService {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(CasService.class);

    public String valid(String ticket, String service) throws InterruptedException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = GlobalConfig.CAS_CENTER_ADDRESS + GlobalConfig.CAS_TICKET_VALIDATE_PATH + "?ticket=" + ticket
                + "&service=" + service;
        Request request = new Request.Builder().url(url).build();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 用户存储结果
        Map<String, String> res = new HashMap<>();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                logger.warn("Cas Validate Fail||{}||{}", e.getMessage(), e.getClass().getName());
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String xml = response.body().string();
                String username = parseXML(xml);
                logger.info("Cas login||{}||{}", username, xml);
                res.put("res", username);
                countDownLatch.countDown();
            }
        });
        countDownLatch.await(GlobalConfig.MAX_IP_API_AWAIT_TIME, TimeUnit.SECONDS);
        return res.get("res");
    }

    private String parseXML(String xml) {
        Document document = null;
        try {
            document  = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            return null;
        }
        Element root = document.getRootElement();
        Iterator<Element> it = root.elementIterator();
        Element successElement = null;
        while (it.hasNext()) {
            Element element = it.next();
            if ("authenticationFailure".equals(element.getName())) {
                logger.warn("Cas Ticket Valid Error||{}", xml);
                return null;
            }
            if ("authenticationSuccess".equals(element.getName())) {
                successElement = element;
                break;
            }
        }
        if (successElement == null) {
            return null;
        }
        it = successElement.elementIterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (GlobalConfig.USERNAME_ELEMENT_NAME.equals(element.getName())) {
                return element.getStringValue();
            }
        }
        return null;
    }


    public String login(String ticket, String service) throws InterruptedException {
        String username = valid(ticket, service);
        if (username == null) {
            return null;
        }

        Integer id = userService.getUserIdByUsername(username);
        // 当前用户不存在，创建对应账户
        if (id == null) {
            UserVO userVO = new UserVO();
            userVO.setUsername(username);
            byte[] passwordBytes = username.getBytes(StandardCharsets.UTF_8);
            String md5Password = DigestUtils.md5DigestAsHex(passwordBytes);
            userVO.setPassword(md5Password);
            id = userService.addUser(userVO);
            logger.info("Cas Create User||{}||{}", id, username);
        }
        // 直接创建用户的SESSION
        return userService.setUserSession(id);
    }
}
