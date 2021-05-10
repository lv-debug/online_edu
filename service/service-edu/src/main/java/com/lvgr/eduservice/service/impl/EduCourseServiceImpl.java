package com.lvgr.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvgr.edubase.exceptionhandle.EduException;
import com.lvgr.eduservice.entity.EduCourse;
import com.lvgr.eduservice.entity.EduCourseDescription;
import com.lvgr.eduservice.entity.EduTeacher;
import com.lvgr.eduservice.entity.grontvo.CourseFrontVo;
import com.lvgr.eduservice.entity.grontvo.CourseWebVo;
import com.lvgr.eduservice.entity.vo.CourseInfoVo;
import com.lvgr.eduservice.entity.vo.CoursePublishVo;
import com.lvgr.eduservice.mapper.EduCourseMapper;
import com.lvgr.eduservice.service.EduChapterService;
import com.lvgr.eduservice.service.EduCourseDescriptionService;
import com.lvgr.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvgr.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;



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

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        EduCourse eduCourse = baseMapper.selectById(courseId);
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表叫数据
        EduCourse eduCourse = new EduCourse();
        //把VO里面的值复制道PO
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if(i <= 0){
            throw new EduException(20001,"修改课程失败");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        boolean b = eduCourseDescriptionService.updateById(eduCourseDescription);
        if(!b){
            throw new EduException(20001,"修改课程简介失败");
        }
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String courseId) {
        CoursePublishVo coursePublishVo = baseMapper.selectCoursePublishVoById(courseId);
        return coursePublishVo;
    }

    @Override
    public Boolean deleteCourse(String courseId) {
        //根据课程id删除小节
        eduVideoService.removeByCourseId(courseId);
        //删除章节
        eduChapterService.removeByCourseId(courseId);
        //删除描述
        eduCourseDescriptionService.removeById(courseId);
        //删除课程
        int i = baseMapper.deleteById(courseId);

        return i > 0;
    }

    @Cacheable(value="eduCourse",key="'selectEduCourse'")
    @Override
    public List<EduCourse> eduCourseList() {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> queryWrapperCourse = new QueryWrapper<>();

        //查询一级分类
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
            queryWrapperCourse.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        //查询二级分类
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
            queryWrapperCourse.eq("subject_id",courseFrontVo.getSubjectId());
        }
        //查询销量（关注度）
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {
            queryWrapperCourse.orderByDesc("buy_count");
        }
        //查询最新
        if(!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {
            queryWrapperCourse.orderByDesc("gmt_create");
        }
        //查询价格
        if(!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
            queryWrapperCourse.orderByDesc("price");
        }
        baseMapper.selectPage(pageCourse,queryWrapperCourse);
        List<EduCourse> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();
        boolean hasNext = pageCourse.hasNext();//下一页
        boolean hasPrevious = pageCourse.hasPrevious();//上一页
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", Integer.parseInt(String.valueOf(pages)));
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }

    @Override
    public CourseWebVo getCourseDescById(String courseId) {
        return baseMapper.getCourseDescById(courseId);
    }
}
