package cn.imessay.speedtest.pojo.vo;

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

    @NotNull(message = "ip不能为空")
    @NotBlank(message = "ip不能为空")
    String ip;

    @NotNull(message = "ua不能为空")
    String ua;

    @NotNull(message = "下载速度不能为空")
    @Min(value = 0)
    Float dl;

    @NotNull(message = "上传速度不能为空")
    @Min(value = 0)
    Float ul;

    @NotNull(message = "ping值不能为空")
    @Min(value = 0)
    Float ping;

    @NotNull(message = "jitter值不能为空")
    @Min(value = 0)
    Float jitter;

    @NotNull(message = "测试节点ID不能为空")
    Integer testPointId;

    String extraAttribute;
}
