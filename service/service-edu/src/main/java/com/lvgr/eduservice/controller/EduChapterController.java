package com.lvgr.eduservice.controller;


import com.lvgr.eduservice.entity.chapter.ChapterVo;
import com.lvgr.eduservice.service.EduChapterService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
@Api(description = "课程章节管理")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "获取所有课程章节和小节")
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId){

        List<ChapterVo> chapterVos = eduChapterService.getChapterVideoByCourseId(courseId);
        return Result.ok().data("chapterVos",chapterVos);
    }

}

