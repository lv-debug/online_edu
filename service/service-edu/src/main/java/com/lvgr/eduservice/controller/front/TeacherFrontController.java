package com.lvgr.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvgr.eduservice.entity.EduCourse;
import com.lvgr.eduservice.entity.EduTeacher;
import com.lvgr.eduservice.service.EduCourseService;
import com.lvgr.eduservice.service.EduTeacherService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lvgr
 * @desc:讲师前台控制层
 * @date 2021/4/24 21:01
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacherfront")
@Api(description = "讲师前台管理")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("getTeacherFrontList/{current}/{limit}")
    @ApiOperation("前台分页获取讲师")
    public Result getTeacherFrontList(@PathVariable long current,@PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        Map<String,Object> map = eduTeacherService.getTeacherFrontList(pageTeacher);
        //返回分页的所有数据。
        return Result.ok().data(map);
    }

    @GetMapping("getTeacherCourseById/{teacherId}")
    @ApiOperation("前台通过id获取讲师")
    public Result getTeacherCourseById(@PathVariable String teacherId) {
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id",teacherId);
        List<EduCourse> courselist = eduCourseService.list(courseQueryWrapper);
        return Result.ok().data("teacher",teacher).data("teacherCourse",courselist);
    }
}
