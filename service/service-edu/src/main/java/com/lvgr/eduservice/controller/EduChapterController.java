package com.lvgr.eduservice.controller;


import com.lvgr.eduservice.entity.EduChapter;
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
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
//@CrossOrigin
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

    @ApiOperation(value = "添加章节")
    @PostMapping("addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){

        eduChapterService.save(eduChapter);
        return Result.ok();
    }


    @ApiOperation(value = "根据章节id查询")
    @GetMapping("getChapter/{chapterId}")
    public Result getChapter(@PathVariable String chapterId){

        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return Result.ok().data("eduChapter",eduChapter);
    }

    @ApiOperation(value = "修改章节")
    @PostMapping("updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){

        eduChapterService.updateById(eduChapter);
        return Result.ok();
    }

    @ApiOperation(value = "删除章节")
    @DeleteMapping("delChapter/{chapterId}")
    public Result delChapter(@PathVariable String chapterId){

        boolean b = eduChapterService.delChapterById(chapterId);
        if(b){
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

