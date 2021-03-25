package com.lvgr.eduservice.service.impl;

import com.lvgr.edubase.exceptionhandle.EduException;
import com.lvgr.eduservice.entity.EduCourse;
import com.lvgr.eduservice.entity.EduCourseDescription;
import com.lvgr.eduservice.entity.vo.CourseInfoVo;
import com.lvgr.eduservice.mapper.EduCourseMapper;
import com.lvgr.eduservice.service.EduCourseDescriptionService;
import com.lvgr.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {

        //1 向课程表叫数据
        EduCourse eduCourse = new EduCourse();
        //把VO里面的值复制道PO
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert <= 0){
            throw new EduException(20001,"添加课程失败");
        }

        //2 向课程简介表加数据
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if(!save){
            throw new EduException(20001,"添加课程简介失败");
        }

        return eduCourse.getId();

    }
}
