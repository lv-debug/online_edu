package com.lvgr.vod.controller;

import com.lvgr.utils.Result;
import com.lvgr.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 视频上传
 */

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
@Api(description = "上传视频")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadAliyVideo")
    @ApiOperation(value = "视频上传阿里云")
    public Result uploadAliyVideo(MultipartFile file) {
        String videoId = vodService.uploadAliyVideo(file);
        return Result.ok().data("videoId",videoId);
    }


}
