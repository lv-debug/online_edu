package com.lvgr.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvgr.eduservice.client.OrderClient;
import com.lvgr.eduservice.entity.EduCourse;
import com.lvgr.eduservice.entity.EduTeacher;
import com.lvgr.eduservice.entity.chapter.ChapterVo;
import com.lvgr.eduservice.entity.grontvo.CourseFrontVo;
import com.lvgr.eduservice.entity.grontvo.CourseWebVo;
import com.lvgr.eduservice.service.EduChapterService;
import com.lvgr.eduservice.service.EduCourseService;
import com.lvgr.eduservice.service.EduTeacherService;
import com.lvgr.utils.JwtUtils;
import com.lvgr.utils.Result;
import com.lvgr.utils.ordervo.CourseWebVoOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author lvgr
 * @desc:课程前台控制层
 * @date 2021/4/25 20:29
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
//@CrossOrigin
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
    @Autowired
    private OrderClient orderClient;

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
    @ApiOperation("前台获取课程详情")
    public Result getCourseDescById(@PathVariable String courseId, HttpServletRequest request) {
        CourseWebVo courseWebVo = eduCourseService.getCourseDescById(courseId);
        List<ChapterVo> chapterVideoByCourse = eduChapterService.getChapterVideoByCourseId(courseId);
        //根据用户id和课程id查询该课程是不是已经当前登录人购买了
        boolean isbBuyCourse = orderClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        //返回分页的所有数据。
        return Result.ok().data("courseWebVo",courseWebVo).data("chapterVideoByCourseId",chapterVideoByCourse)
                .data("isbBuyCourse",isbBuyCourse);
    }

    @PostMapping("getCourseByOrder/{id}")
    @ApiOperation("通过课程id获取课程")
    public CourseWebVoOrder getCourseByOrder(@PathVariable String id) {
        CourseWebVo course = eduCourseService.getCourseDescById(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(course,courseWebVoOrder);
        //返回分页的所有数据。
        return courseWebVoOrder;
    }
}
