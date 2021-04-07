package com.lvgr.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *  * @author lvgr
 *  * @date 2021/4/6 22:11
 *  * @desc 视频点播启动类
 * exclude = DataSourceAutoConfiguration.class:禁止springboot自动注入数据源配置,由于做oss文件处理，所以不需要关联数据库。如果不加会报错。
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.lvgr"})
public class VodApplication {

    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);
    }
}
