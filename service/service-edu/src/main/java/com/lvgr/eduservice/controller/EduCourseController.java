package com.lvgr.eduservice.controller;


import com.lvgr.eduservice.entity.vo.CourseInfoVo;
import com.lvgr.eduservice.entity.vo.CoursePublishVo;
import com.lvgr.eduservice.service.EduCourseService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-course")
@Api(description = "课程管理")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

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

    @PostMapping("updateCourseInfo")
    @ApiOperation("修改课程")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        eduCourseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }

    @PostMapping("getCoursePublishVoById/{courseId}")
    @ApiOperation("根据课程id查询课程发布信息")
    public Result getCoursePublishVoById(@PathVariable String courseId) {

        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishVoById(courseId);
        return Result.ok().data("coursePublishVo",coursePublishVo);
    }




}

