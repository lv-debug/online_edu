package com.lvgr.eduservice.client;

import com.lvgr.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 删除阿里云视频调用工具接口类
 * Component声明被spring管理的组件
 * FeignClient表示一个服务调用 FeignClient("service-vod") 此处 ""内为service-vod的项目的配置文件的spring.application.name=service-vod
 */

@Component
@FeignClient(name = "service-vod",fallback = VodClientErrorImpl.class)
public interface VodClient {

    //定义调用的方法路径(完全路径)
    //根据视频id删除视频
    //@PathVariable("videoId") 此处一定要加参数 否则会出错
    @DeleteMapping("/eduvod/video/removeAliyVod/{videoId}")
    public Result removeAliyVod(@PathVariable("videoId") String videoId);

    //批量删除视频
    @DeleteMapping("/eduvod/video/deleteBatch")
    public Result deleteBatch(@RequestParam("videoIds") List<String> videoIds);

}
