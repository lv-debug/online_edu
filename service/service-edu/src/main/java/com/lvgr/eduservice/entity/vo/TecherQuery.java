package com.lvgr.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lvgr
 * @date 2021/3/10 16:48
 * @desc
 */

@Data
public class TecherQuery {

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间" , example="2020-12-12 12:00:00")
    private String begin;

    @ApiModelProperty(value = "查询结束时间" , example="2020-12-22 12:00:00")
    private String end;
}
