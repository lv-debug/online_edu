package com.lvgr.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.ram.model.v20150501.DeleteAccessKeyRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.lvgr.edubase.exceptionhandle.EduException;
import com.lvgr.utils.JwtUtils;
import com.lvgr.utils.Result;
import com.lvgr.vod.service.VodService;
import com.lvgr.vod.utils.ConstantVodUtils;
import com.lvgr.vod.utils.InitVodClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 阿里云视频点播
 *
 * @author lvgr
 * @since 2021-04-22
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */

@RestController
@RequestMapping("/eduvod/video")
//@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadAlyiVideo")
    @ApiOperation("上传视频到阿里云")
    public Result uploadAlyiVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadAliyVideo(file);
        return Result.ok().data("videoId",videoId);
    }

    @DeleteMapping("removeAliyVod/{videoId}")
    @ApiOperation("根据视频id删除阿里云视频")
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

    @DeleteMapping("deleteBatch")
    @ApiOperation("删除多个阿里云视频")
    public Result deleteBatch(@RequestParam("videoIds") List<String> videoIds) {
        vodService.deleteBatch(videoIds);
        return Result.ok();
    }


    @GetMapping("getAlyiVideoPlayAuth/{videoId}")
    @ApiOperation("根据视频id获取视频凭证")
    public Result getAlyiVideoPlayAuth(@PathVariable String videoId){
        try{
            DefaultAcsClient defaultAcsClient = InitVodClient.initVodClient(ConstantVodUtils.KEY_ID, ConstantVodUtils.KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
            //想request对象里面设置视频id
            request.setVideoId(videoId);
            //调用初始化对象的方法，传递request，获取数据
            response =  defaultAcsClient.getAcsResponse(request);

            //Base信息
            System.out.print("getPlayAuth = " + response.getPlayAuth() + "\n");
            return Result.ok().data("playAuth",response.getPlayAuth());
        }catch (Exception e){
            return Result.error();
        }
    }


}
