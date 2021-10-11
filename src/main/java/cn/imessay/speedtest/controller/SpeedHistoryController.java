package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.dao.history.HistoryInfoDO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 该接口提供测速数据展示、计算等接口
 */
@RestController
@RequestMapping("/data_history")
public class SpeedHistoryController {

    @RequestMapping({"/", ""})
    public List<HistoryInfoDO> history() {
        return null;
    }
}
