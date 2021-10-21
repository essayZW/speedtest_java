package cn.imessay.speedtest.service.history;

import cn.imessay.speedtest.dao.history.SpeedHistoryDAO;
import cn.imessay.speedtest.dao.history.SpeedHistoryDO;
import cn.imessay.speedtest.exception.UserNotFoundException;
import cn.imessay.speedtest.pojo.dto.SpeedHistoryDTO;
import cn.imessay.speedtest.pojo.vo.PageQueryVO;
import cn.imessay.speedtest.pojo.vo.SpeedHistoryVO;
import cn.imessay.speedtest.pojo.dto.UserDTO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private SpeedHistoryDAO speedHistoryDAO;

    /**
     * 增加一条测速历史信息
     * @param historyVO 测速历史信息
     * @param userDTO 添加的用户
     * @return 插入的信息在数据库中的主键ID，若为null则插入失败
     */
    public Long add(SpeedHistoryVO historyVO, UserDTO userDTO) throws UserNotFoundException {
        if (userDTO == null) {
            throw new UserNotFoundException("User can't null");
        }
        if (historyVO == null) {
            return null;
        }
        SpeedHistoryDO speedHistoryDO = new SpeedHistoryDO();
        speedHistoryDO.setIp(historyVO.getIp());
        speedHistoryDO.setUa(historyVO.getUa());
        speedHistoryDO.setDl(historyVO.getDl());
        speedHistoryDO.setUl(historyVO.getUl());
        speedHistoryDO.setPing(historyVO.getPing());
        speedHistoryDO.setJitter(historyVO.getJitter());
        speedHistoryDO.setTestPointId(historyVO.getTestPointId());
        speedHistoryDO.setExtraAttribute(JSONObject.parseObject(historyVO.getExtraAttribute()));
        return speedHistoryDAO.insert(speedHistoryDO, userDTO);
    }


    /**
     * 分页查询某个用户的测速历史记录
     * @param userDTO 用户信息
     * @param pageQueryVO 分页信息
     */
    public List<SpeedHistoryDTO> queryByUser(UserDTO userDTO, PageQueryVO pageQueryVO) {
        return speedHistoryDAO.queryByUser(userDTO, pageQueryVO);
    }
}
