package com.lvgr.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.eduservice.client.VodClient;
import com.lvgr.eduservice.entity.EduChapter;
import com.lvgr.eduservice.entity.EduVideo;
import com.lvgr.eduservice.service.EduVideoService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
@Api(description = "课程小节管理")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    @ApiOperation("新增小节")
    @PostMapping("addEduVideo")
    private Result addEduVideo(@RequestBody EduVideo eduVideo) {

        eduVideoService.save(eduVideo);
        return Result.ok();
    }

    @ApiOperation("修改小节")
    @PostMapping("updateEduVideo")
    private Result updateEduVideo(@RequestBody EduVideo eduVideo) {

        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }

    @ApiOperation("删除小节")
    @DeleteMapping("delEduVideo/{videoId}")
    private Result delEduVideo(@PathVariable String videoId) {
        //根据小节Id获取视频Id
        EduVideo eduVideo = eduVideoService.getById(videoId);
        if(!StringUtils.isEmpty(eduVideo.getVideoSourceId())) {
            vodClient.removeAliyVod(eduVideo.getVideoSourceId());
        }
        boolean b = eduVideoService.removeById(videoId);
        if(b) {
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @ApiOperation(value = "根据小节id查询")
    @GetMapping("getVideo/{videoId}")
    public Result getVideo(@PathVariable String videoId){

        EduVideo eduVideo = eduVideoService.getById(videoId);
        return Result.ok().data("eduVideo",eduVideo);
    }

}

