package com.lvgr.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lvgr
 * @date 2021/3/10 16:48
 * @desc
 */

@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程姓名")
    private String title;

    @ApiModelProperty(value = "课程状态 Normal：已发布 Draft：未发布")
    private String status;
}
