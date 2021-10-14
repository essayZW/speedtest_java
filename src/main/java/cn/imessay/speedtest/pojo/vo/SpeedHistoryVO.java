package cn.imessay.speedtest.pojo.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class SpeedHistoryVO {

    String ip;

    String ua;

    Float dl;

    Float ul;

    Float ping;

    Float jitter;

    Integer testPointId;

    JSONObject extraAttribute;
}
