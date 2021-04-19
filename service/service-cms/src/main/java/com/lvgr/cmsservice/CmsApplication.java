package com.lvgr.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lvgr
 * @desc 用于前台的展示业务
 * 在启动类上面加@MapperScan("com.lvgr.cmsservice.mapper")，是连接数据库
 * 可以把mapper注入进来。还可以和edu项目里面的EduConfig，方式也可以。
 * 两种方式都可以，EduConfig这种方式是因为在里面加入了逻辑删除插件和分页插件，所以编写了配置类。
 * @date 2021/4/17 23:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.lvgr"})
@MapperScan("com.lvgr.cmsservice.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
