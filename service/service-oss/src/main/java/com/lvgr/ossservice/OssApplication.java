package com.lvgr.ossservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lvgr
 * @date 2021/3/15 22:11
 * @desc 用于文件上传到阿里云OSS业务
 * exclude = DataSourceAutoConfiguration.class:禁止springboot自动注入数据源配置,由于做oss文件处理，所以不需要关联数据库。如果不加会报错。
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.lvgr"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
