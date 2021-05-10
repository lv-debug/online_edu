package com.lvgr.orderservice.client;

import com.lvgr.utils.Result;
import com.lvgr.utils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 课程信息调用工具接口类
 * Component声明被spring管理的组件
 * FeignClient表示一个服务调用 FeignClient("service-edu") 此处 ""内为service-edu的项目的配置文件的spring.application.name=service-vod
 */

@Component
@FeignClient(name = "service-edu")
public interface EduClient {

    //定义调用的方法路径(完全路径)
    //根据课程id获取课程
    //@PathVariable("id") 此处一定要加参数 否则会出错
    @PostMapping("/eduservice/coursefront/getCourseByOrder/{id}")
    public CourseWebVoOrder getCourseByOrder(@PathVariable("id") String id);

}
