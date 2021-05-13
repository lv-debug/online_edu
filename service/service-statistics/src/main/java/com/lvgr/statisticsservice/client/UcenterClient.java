package com.lvgr.statisticsservice.client;

import com.lvgr.utils.Result;
import com.lvgr.utils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 用户远程调用工具接口类
 * Component声明被spring管理的组件
 * FeignClient表示一个服务调用 FeignClient("service-ucenter") 此处 ""内为service-ucenter的项目的配置文件的spring.application.name=service-ucenter
 */

@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClient {

    //定义调用的方法路径(完全路径)
    //根据用户id获取用户信息
    //@PathVariable("day") 此处一定要加参数 否则会出错
    @GetMapping("/ucenterservice/ucenter-member/countRegister/{day}")
    public Result countRegister(@PathVariable("day") String day);

}
