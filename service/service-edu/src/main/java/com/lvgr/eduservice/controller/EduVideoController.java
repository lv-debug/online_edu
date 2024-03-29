package com.lvgr.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.edubase.exceptionhandle.EduException;
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
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
@RestController
@RequestMapping("/eduservice/edu-video")
//@CrossOrigin
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
            Result result = vodClient.removeAliyVod(eduVideo.getVideoSourceId());
            if(result.getCode() == 20001) {
                throw new EduException(20001,"删除视频失败，熔断器");
            }
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

