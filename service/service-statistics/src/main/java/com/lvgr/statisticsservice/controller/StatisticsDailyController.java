package com.lvgr.statisticsservice.controller;


import com.lvgr.statisticsservice.service.StatisticsDailyService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-05-12
 */
@RestController
@RequestMapping("/statisticsservice/statistics-daily")
@CrossOrigin
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


}

