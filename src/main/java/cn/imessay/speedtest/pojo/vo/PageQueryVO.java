package cn.imessay.speedtest.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class PageQueryVO {
    @NotNull(message = "缺少页面大小参数")
    @Min(value = 1, message = "页大小必须为正整数")
    private Integer size;

    @NotNull(message = "缺少页码参数")
    @Min(value = 1, message = "页码必须为正整数")
    private Integer index;
}
