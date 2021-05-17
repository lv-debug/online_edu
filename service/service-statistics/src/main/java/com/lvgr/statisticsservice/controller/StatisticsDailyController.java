package com.lvgr.statisticsservice.controller;


import com.lvgr.statisticsservice.service.StatisticsDailyService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-05-12
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
@RestController
@RequestMapping("/statisticsservice/statistics-daily")
//@CrossOrigin
@Api(description = "网站统计日数据")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @PostMapping("countRegisterCount/{day}")
    @ApiOperation("根据日期统计注册人数")
    public Result countRegisterCount (@PathVariable String day) {
        statisticsDailyService.countRegisterCount(day);
        return Result.ok();
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    @ApiOperation("根据开始日期、结束日期、图标类型查询数据图标")
    public Result showData(@PathVariable String type,@PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = statisticsDailyService.getShowData(type,begin,end);
        return Result.ok().data(map);
    }


}

