package com.lvgr.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lvgr
 * @date 2021/3/24 14:10
 * @desc:课程发布
 */
@ApiModel(value = "课程发布", description = "课程发布")
@Data
public class CoursePublishVo {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String cover;

    private Integer lessonNum;

    private String subjectLevelOne;

    private String subjectLevelTwo;

    private String teacherName;

    private String price;


}
