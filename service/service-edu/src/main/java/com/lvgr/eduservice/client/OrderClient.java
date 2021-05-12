package com.lvgr.eduservice.client;

import com.lvgr.utils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 课程信息调用工具接口类
 * Component声明被spring管理的组件
 * FeignClient表示一个服务调用 FeignClient("service-order") 此处 ""内为service-edu的项目的配置文件的spring.application.name=service-order
 */

@Component
@FeignClient(name = "service-order")
public interface OrderClient {

    //定义调用的方法路径(完全路径)
    //根据课程id获取课程
    //@PathVariable("courseId") 此处一定要加参数 否则会出错
    @GetMapping("/orderservice/t-order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId,@PathVariable("memberId") String memberId);

}
