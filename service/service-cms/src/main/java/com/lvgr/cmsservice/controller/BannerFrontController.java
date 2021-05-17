package com.lvgr.cmsservice.controller;


import com.lvgr.cmsservice.entity.CrmBanner;
import com.lvgr.cmsservice.service.CrmBannerService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-04-17
 * @desc 前台页面显示
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
@RestController
@RequestMapping("/cmsservice/crm-banner")
//@CrossOrigin
@Api(description = "轮换图控制类（前台页面显示）")
public class BannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getAllBanner")
    @ApiOperation("获取所有Banner")
    private Result getAllBanner(){
        List<CrmBanner> banners = crmBannerService.selectAllBanner();
        return Result.ok().data("list",banners);

    }

}

