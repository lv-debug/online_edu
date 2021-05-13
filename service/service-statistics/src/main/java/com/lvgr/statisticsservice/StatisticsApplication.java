package com.lvgr.statisticsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lvgr
 * @date 2021/5/07 22:11
 * @desc 用于网站统计
 *
 * 默认只加载当前包的所在类，需要扫描别的包需要加上@ComponentScan(basePackages = {"com.lvgr"})
 * EnableDiscoveryClient 表示nacos注册,远程调用
 * @EnableFeignClients 表示在调用段服务调用的注解
 * EnableScheduling 定时任务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@ComponentScan(basePackages = {"com.lvgr"})
@MapperScan("com.lvgr.statisticsservice.mapper")
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class,args);
    }
}
