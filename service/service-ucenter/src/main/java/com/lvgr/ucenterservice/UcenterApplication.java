package com.lvgr.ucenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lvgr
 * @date 2021/4/22 22:11
 * @desc 用于用户中心，包含登录、注册等
 *
 * 默认只加载当前包的所在类，需要扫描别的包需要加上@ComponentScan(basePackages = {"com.lvgr"})
 * EnableDiscoveryClient 表示nacos注册
 * @EnableFeignClients 表示在调用段服务调用的注解
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.lvgr"})
@MapperScan("com.lvgr.ucenterservice.mapper")
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
