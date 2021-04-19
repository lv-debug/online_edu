package com.lvgr.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lvgr
 * @date 2020/7/29 21:54
 * @desc 该类是连接数据库的配置类，还可以在启动类上面加@MapperScan("com.lvgr.cmsservice.mapper")，
 * 也可以把mapper注入进来。两种方式都可以，当前这种方式是因为在里面加入了逻辑删除插件和分页插件，所以编写了配置类。
 */
@Configuration
@MapperScan("com.lvgr.eduservice.mapper")
public class EduConfig {

    /**
     * 注入逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 注入分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
