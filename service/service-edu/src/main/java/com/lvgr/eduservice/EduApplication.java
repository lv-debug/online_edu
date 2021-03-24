package com.lvgr.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lvgr
 * @date 2020/7/29 21:51
 * @desc
 */

/**
 * 默认只加载当前包的所在类，需要扫描别的包需要加上@ComponentScan(basePackages = {"com.lvgr"})
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lvgr"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
