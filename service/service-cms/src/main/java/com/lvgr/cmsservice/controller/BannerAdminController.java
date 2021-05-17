package com.lvgr.cmsservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvgr.cmsservice.entity.CrmBanner;
import com.lvgr.cmsservice.service.CrmBannerService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-04-17
 * @desc 后台管理员控制
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
@RestController
@RequestMapping("/cmsservice/crm-banner")
//@CrossOrigin
@Api(description = "轮换图控制类（后台管理员控制）")
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation("分页查询轮换图")
    @GetMapping("pageCourse/{current}/{limit}")
    public Result pageListCourse(@PathVariable @ApiParam(name = "current",value = "当前页",required = true) long current,
                                 @PathVariable @ApiParam(name = "limit",value = "当前页总数",required = true) long limit) {

        Page<CrmBanner> crmBannerPage = new Page<>(current,limit);
        crmBannerService.page(crmBannerPage, null);

        Map map = new HashMap();
        map.put("total",crmBannerPage.getTotal());
        map.put("records",crmBannerPage.getRecords());

        return Result.ok().data(map);
    }

    @ApiOperation("添加banner")
    @PostMapping("addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {

        crmBannerService.save(crmBanner);
        return Result.ok();
    }

    @ApiOperation("根据id查询banner")
    @GetMapping("getBanner/{id}")
    public Result getBanner(@PathVariable @ApiParam(name = "id",value = "banner id",required = true) String id) {
        CrmBanner crmBanner = crmBannerService.getById(id);
        return Result.ok().data("crmBanner",crmBanner);
    }

    @ApiOperation("修改banner")
    @PostMapping("updBanner")
    public Result updBanner(@RequestBody CrmBanner crmBanner) {
        boolean isCrmBanner = crmBannerService.updateById(crmBanner);
        if(isCrmBanner){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @ApiOperation("逻辑删除banner")
    @DeleteMapping("{id}")
    public Result deleteCrmBanner(@PathVariable @ApiParam(name = "id",value = "banner id",required = true) String id) {
        boolean b = crmBannerService.removeById(id);
        if(b){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

}

