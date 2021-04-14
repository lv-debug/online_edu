package com.lvgr.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.ram.model.v20150501.DeleteAccessKeyRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.lvgr.edubase.exceptionhandle.EduException;
import com.lvgr.utils.Result;
import com.lvgr.vod.service.VodService;
import com.lvgr.vod.utils.ConstantVodUtils;
import com.lvgr.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public Result uploadAlyiVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadAliyVideo(file);
        return Result.ok().data("videoId",videoId);
    }

    @DeleteMapping("removeAliyVod/{videoId}")
    public Result removeAliyVod(@PathVariable String videoId) {
        try {
            DefaultAcsClient initVodClient = InitVodClient.initVodClient(ConstantVodUtils.KEY_ID,ConstantVodUtils.KEY_SECRET);
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            deleteVideoRequest.setVideoIds(videoId);
            initVodClient.getAcsResponse(deleteVideoRequest);
            return Result.ok();
        }catch (Exception e) {
            e.printStackTrace();
            throw new EduException(20001,"删除阿里云视频失败");
        }
    }

    //删除多个阿里云视频
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("videoIds") List<String> videoIds) {
        vodService.deleteBatch(videoIds);
        return Result.ok();
    }
}
