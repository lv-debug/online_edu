package com.lvgr.orderservice.controller;


import com.lvgr.orderservice.service.TPayLogService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/orderservice/tPayLog")
@CrossOrigin
@Api(description = "微信支付管理")
public class TPayLogController {

    @Autowired
    private TPayLogService tPayLogService;

    //生成微信支付二维码
    @GetMapping("createRWCode/{orderNo}")
    @ApiOperation("根据订单号生成二维码")
    public Result createRWCode(@PathVariable String orderNo){
        Map map = tPayLogService.createRWCode(orderNo);

        return Result.ok();
    }

}

