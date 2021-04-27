package com.lvgr.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvgr.eduservice.entity.EduCourse;
import com.lvgr.eduservice.entity.EduTeacher;
import com.lvgr.eduservice.entity.chapter.ChapterVo;
import com.lvgr.eduservice.entity.grontvo.CourseFrontVo;
import com.lvgr.eduservice.service.EduChapterService;
import com.lvgr.eduservice.service.EduCourseService;
import com.lvgr.eduservice.service.EduTeacherService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lvgr
 * @desc:课程前台控制层
 * @date 2021/4/25 20:29
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/coursefront")
@Api(description = "讲师前台管理")
public class CourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduTeacherService eduTeacherService;

    @PostMapping("getCourseFrontList/{current}/{limit}")
    @ApiOperation("前台分页获取课程")
    public Result getCourseFrontList(@PathVariable long current, @PathVariable long limit,
                                      @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        Map<String,Object> map = eduCourseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页的所有数据。
        return Result.ok().data(map);
    }

    @GetMapping("getCourseDescById/{courseId}")
    @ApiOperation("前台分页获取课程")
    public Result getCourseDescById(@PathVariable String courseId) {
        EduCourse eduCourse = eduCourseService.getCourseDescById(courseId);
        List<ChapterVo> chapterVideoByCourseId = eduChapterService.getChapterVideoByCourseId(courseId);
        EduTeacher eduTeacher = eduTeacherService.getById(eduCourse.getTeacherId());
        //返回分页的所有数据。
        return Result.ok();
    }
}
