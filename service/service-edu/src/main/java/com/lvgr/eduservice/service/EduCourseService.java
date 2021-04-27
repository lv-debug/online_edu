package com.lvgr.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvgr.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvgr.eduservice.entity.grontvo.CourseFrontVo;
import com.lvgr.eduservice.entity.vo.CourseInfoVo;
import com.lvgr.eduservice.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

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

    Boolean deleteCourse(String courseId);

    List<EduCourse> eduCourseList();

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    EduCourse getCourseDescById(String courseId);
}
