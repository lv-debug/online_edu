package com.lvgr.eduservice.mapper;

import com.lvgr.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvgr.eduservice.entity.grontvo.CourseWebVo;
import com.lvgr.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 根据课程id查询课程基本信息
     */
    CoursePublishVo selectCoursePublishVoById(String courseId);

    CourseWebVo getCourseDescById(String courseId);
}
