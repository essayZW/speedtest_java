package cn.imessay.speedtest.pojo.dto;

import cn.imessay.speedtest.dao.history.SpeedHistoryDO;
import cn.imessay.speedtest.dao.point.TestPointDO;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class SpeedHistoryDTO {
    private Long id;
    private Date time;
    private String ip;
    private String ua;
    private Float dl;
    private Float ul;
    private Float ping;
    private Float jitter;
    private JSONObject extraAttribute;

    private Integer userid;
    private String username;
    private JSONObject userExtraAttribute;

    private Integer testPointId;
    private String testPointName;


    public static SpeedHistoryDTO build(SpeedHistoryDO speedHistoryDO,
                                        UserDTO userDTO,
                                        TestPointDO testPointDO)
    {
        SpeedHistoryDTO speedHistoryDTO = new SpeedHistoryDTO();
        speedHistoryDTO.setId(speedHistoryDO.getId());
        speedHistoryDTO.setTime(speedHistoryDO.getTime());
        speedHistoryDTO.setIp(speedHistoryDO.getIp());
        speedHistoryDTO.setUa(speedHistoryDO.getUa());
        speedHistoryDTO.setDl(speedHistoryDO.getDl());
        speedHistoryDTO.setUl(speedHistoryDO.getUl());
        speedHistoryDTO.setPing(speedHistoryDO.getPing());
        speedHistoryDTO.setJitter(speedHistoryDO.getJitter());
        speedHistoryDTO.setExtraAttribute(speedHistoryDO.getExtraAttribute());

        speedHistoryDTO.setUserid(userDTO.getId());
        speedHistoryDTO.setUsername(userDTO.getUsername());
        speedHistoryDTO.setUserExtraAttribute(userDTO.getExtraAttribute());

        speedHistoryDTO.setTestPointId(testPointDO.getId());
        speedHistoryDTO.setTestPointName(testPointDO.getName());
        return speedHistoryDTO;

    }
}
