package com.lvgr.eduservice.service;

import com.lvgr.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvgr.eduservice.entity.vo.CourseInfoVo;
import com.lvgr.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVoById(String courseId);
}
