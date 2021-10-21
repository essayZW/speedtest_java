package cn.imessay.speedtest.controller;

import cn.imessay.speedtest.annoation.UserLogin;
import cn.imessay.speedtest.config.ErrorCode;
import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.exception.UserNotFoundException;
import cn.imessay.speedtest.pojo.dto.SpeedHistoryDTO;
import cn.imessay.speedtest.pojo.vo.PageQueryVO;
import cn.imessay.speedtest.pojo.vo.SpeedHistoryVO;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import cn.imessay.speedtest.response.BaseResponseBody;
import cn.imessay.speedtest.service.history.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private Logger logger = LoggerFactory.getLogger(HistoryController.class);

    @Autowired
    private HistoryService historyService;

    @PostMapping
    @UserLogin
    public BaseResponseBody<Object> add(@Validated SpeedHistoryVO historyVO, ModelAndView modelAndView) {
        Map<String, Object> model = modelAndView.getModel();
        Long id = null;
        UserDTO userDTO = (UserDTO) model.get(GlobalConfig.MODEL_USER_KEY);
        try {
            id = historyService.add(historyVO, userDTO);
        } catch (UserNotFoundException e) {
            return BaseResponseBody.error(ErrorCode.USER_NOT_FOUND);
        }
        logger.info("Store New Speed History:{}||{}", historyVO.toString(), userDTO.toString());
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        if (id != null) {
            return BaseResponseBody.ok(response);
        }
        else {
            return BaseResponseBody.error(ErrorCode.ADD_SPEED_HISTORY_FAIL);
        }
    }

    @GetMapping("/{username}")
    @UserLogin
    public BaseResponseBody<Object> queryByUser(@PathVariable("username") String username,
                                                 @Validated PageQueryVO pageQueryVO,
                                                 ModelAndView modelAndView) {
        Object user = modelAndView.getModel().get(GlobalConfig.MODEL_USER_KEY);
        if (user == null) {
            return BaseResponseBody.error(ErrorCode.USER_NOT_LOGIN);
        }
        List<SpeedHistoryDTO> speedHistoryDTOS = historyService.queryByUser((UserDTO) user, pageQueryVO);
        return BaseResponseBody.ok(speedHistoryDTOS);
    }

}
