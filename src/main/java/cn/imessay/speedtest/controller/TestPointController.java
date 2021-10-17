package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.dao.point.TestPointDO;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.point.TestPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/testpoint")
public class TestPointController {

    @Autowired
    private TestPointService testPointService;

    @GetMapping
    public BaseResponseBody<List<TestPointDO>> query() {
        return BaseResponseBody.ok(testPointService.queryAvailable());
    }
}
