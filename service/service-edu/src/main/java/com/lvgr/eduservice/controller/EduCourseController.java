package com.lvgr.eduservice.controller;


import com.lvgr.eduservice.entity.vo.CourseInfoVo;
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




}

