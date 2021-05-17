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
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
@RestController
@RequestMapping("/orderservice/tPayLog")
//@CrossOrigin
@Api(description = "微信支付管理")
public class TPayLogController {

    @Autowired
    private TPayLogService tPayLogService;

    @GetMapping("createRWCode/{orderNo}")
    @ApiOperation("根据订单号生成二维码")
    public Result createRWCode(@PathVariable String orderNo){
        Map map = tPayLogService.createRWCode(orderNo);
        return Result.ok().data(map);
    }

    @GetMapping("queryRWCode/{orderNo}")
    @ApiOperation("根据订单号查询支付状态")
    public Result queryRWCode(@PathVariable String orderNo){
        Map<String,String> map = tPayLogService.queryRWCode(orderNo);
        if (map == null) {
            return Result.error().message("支付出错");
        }
        //支付成功
        if (map.get("trade_state").equals("SUCCESS")) {
            tPayLogService.updateOrderStatus(map);
            return Result.ok();
        }
        return Result.ok().code(25000).message("支付中");
    }

}

