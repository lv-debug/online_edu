package com.lvgr.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lvgr
 * @since 2020-07-29
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> eduTeacherList();
}
