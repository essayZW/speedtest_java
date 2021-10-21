package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.config.ErrorCode;
import cn.imessay.speedtest.exception.InvalidConfigNameException;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.config.GlobalConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/config")
public class GlobalConfigController {

    private Logger logger = LoggerFactory.getLogger(GlobalConfigController.class);

    @Autowired
    private GlobalConfigService globalConfigService;

    @GetMapping("/{configName}")
    public BaseResponseBody<Object> querySingle(@PathVariable("configName") String configName) {
        Map<String, Object> responseData = new HashMap<>();
        Object value = null;
        try {
            value = globalConfigService.get(configName);
        } catch (InvalidConfigNameException e) {
            logger.warn("Invalid Config Name:{}", configName);
            return BaseResponseBody.error(ErrorCode.INVALID_CONFIG_NAME);
        }
        responseData.put("value", value);
        return BaseResponseBody.ok(responseData);
    }

    @PutMapping("/{configName}")
    public BaseResponseBody<Object> update(@PathVariable("configName") String configName,
                                           @RequestParam String value) {
        if (globalConfigService.set(configName, value)) {
            return BaseResponseBody.ok("success");
        }
        else {
            return BaseResponseBody.error(ErrorCode.CONFIG_UPDATE_FAIL);
        }

    }


    /**
     * 获取app的基本信息
     */
    @GetMapping("/appinfo")
    public BaseResponseBody<Object> getAppDefaultInfo() {
        Map<String, Object> responseData = new HashMap<>();
        List<String> configs = initAppConfigNames();
        for (String name : configs) {
            try {
                responseData.put(name, globalConfigService.get(name));
            } catch (InvalidConfigNameException e) {
                e.printStackTrace();
            }
        }
        return BaseResponseBody.ok(responseData);
    }

    private List<String> initAppConfigNames() {
        List<String> configNames = new ArrayList<>();
        configNames.add("WEBAPP_NAME");
        return configNames;
    }
}
