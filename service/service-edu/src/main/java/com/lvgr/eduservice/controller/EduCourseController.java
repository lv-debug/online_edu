package com.lvgr.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvgr.eduservice.entity.EduCourse;
import com.lvgr.eduservice.entity.vo.CourseInfoVo;
import com.lvgr.eduservice.entity.vo.CoursePublishVo;
import com.lvgr.eduservice.entity.vo.CourseQuery;
import com.lvgr.eduservice.service.EduCourseService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
//@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-course")
@Api(description = "课程管理")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation("查询课程列表")
    @GetMapping("findAllCourse")
    public Result findAllCourse(){
        List<EduCourse> list = eduCourseService.list(null);
        return Result.ok().data("list",list);
    }


    @ApiOperation("分页查询课程")
    @GetMapping("pageCourse/{current}/{limit}")
    public Result pageListCourse(@PathVariable @ApiParam(name = "current",value = "当前页",required = true) long current,
                                  @PathVariable @ApiParam(name = "limit",value = "当前页总数",required = true) long limit) {

        Page<EduCourse> eduCoursePage = new Page<>(current,limit);
        eduCourseService.page(eduCoursePage, null);

        Map map = new HashMap();
        map.put("total",eduCoursePage.getTotal());
        map.put("records",eduCoursePage.getRecords());

        return Result.ok().data(map);
    }


    @ApiOperation("分页带其他查询课程")
    @PostMapping("pageCourseQuery/{current}/{limit}")
    public Result pageCourseQuery(@PathVariable @ApiParam(name = "current",value = "当前页",required = true) long current,
                                   @PathVariable @ApiParam(name = "limit",value = "当前页总数",required = true) long limit,
                                   @RequestBody(required = false) CourseQuery courseQuery ) {

        Page<EduCourse> pageCourse = new Page<>(current,limit);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        if(!StringUtils.isEmpty(title)){
            queryWrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)){
            queryWrapper.eq("status",status);
        }

        queryWrapper.orderByDesc("gmt_create");

        eduCourseService.page(pageCourse, queryWrapper);

        Map map = new HashMap();
        map.put("total",pageCourse.getTotal());
        map.put("rows",pageCourse.getRecords());

        return Result.ok().data(map);
    }

    @PostMapping("addCourseInfo")
    @ApiOperation("新增课程")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        String cId = eduCourseService.addCourseInfo(courseInfoVo);
        return Result.ok().data("cId",cId);
    }

    @GetMapping("getCourseInfo/{courseId}")
    @ApiOperation("根据id获取课程")
    public Result getCourseInfo(@PathVariable String courseId) {

        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo",courseInfoVo);
    }

    @DeleteMapping("deleteCourse/{courseId}")
    @ApiOperation("根据id删除课程")
    public Result deleteCourse(@PathVariable String courseId) {

        Boolean b = eduCourseService.deleteCourse(courseId);
        if(b){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @PostMapping("updateCourseInfo")
    @ApiOperation("修改课程")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        eduCourseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }

    @GetMapping("getCoursePublishVoById/{courseId}")
    @ApiOperation("根据课程id查询课程发布信息")
    public Result getCoursePublishVoById(@PathVariable String courseId) {

        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishVoById(courseId);
        return Result.ok().data("coursePublishVo",coursePublishVo);
    }

    @GetMapping("publishCourse/{courseId}")
    @ApiOperation("修改课程")
    public Result publishCourse(@PathVariable String courseId) {

        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.ok();
    }



}

