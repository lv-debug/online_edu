package com.lvgr.eduservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author lvgr
 * @since 2020-07-29
 * @EqualsAndHashCode 此注解会生成equals(Object other) 和 hashCode()方法
 * @Accessors(chain=true) 通过该注解可以控制getter和setter方法的形式。使用chain属性，setter方法返回当前对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EduTeacher对象", description="讲师")
public class EduTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自动生成策略
     */
    @ApiModelProperty(value = "讲师ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师简介")
    private String intro;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    @ApiModelProperty(value = "排序")

    private Integer sort;

    /**
     * @TableLogic配置的逻辑删除
     * @TableLogic可以使mybatis的查询方法会自动在sql里面 where is_delete = 0
     */
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;

    /**
     * @TableField，fill = FieldFill.INSERT在做insert操作时候，数据库会自动插入数据值。
     * 通过MyMetaObjectHandle的insertFill实现
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * @TableField，fill = FieldFill.UPDATE在做update操作时候，数据库会自动更新数据值。
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
