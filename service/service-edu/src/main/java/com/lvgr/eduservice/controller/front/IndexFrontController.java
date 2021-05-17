package com.lvgr.eduservice.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.eduservice.entity.EduCourse;
import com.lvgr.eduservice.entity.EduTeacher;
import com.lvgr.eduservice.service.EduCourseService;
import com.lvgr.eduservice.service.EduTeacherService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用于课程、讲师的前台业务
 * </p>
 *
 * @author lvgr
 * @since 2021-04-18
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
//@CrossOrigin
@RestController
@RequestMapping("/eduservice/indexfront")
@Api(description = "首页课程讲师前台管理")
public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("index")
    @ApiOperation("查询热门课程和名师")
    public Result index() {
        List<EduCourse> eduCourses = eduCourseService.eduCourseList();
        List<EduTeacher> eduTeachers = eduTeacherService.eduTeacherList();
        return Result.ok().data("eudCourseList",eduCourses).data("eduTeacherList",eduTeachers);
    }


}

