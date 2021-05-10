package com.lvgr.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.orderservice.entity.TOrder;
import com.lvgr.orderservice.service.TOrderService;
import com.lvgr.utils.JwtUtils;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-05-07
 */
@CrossOrigin
@RestController
@RequestMapping("/orderservice/t-order")
@Api(description = "订单管理")
public class TOrderController {

    @Autowired
    private TOrderService tOrderService;

    @PostMapping("createOrder/{courseId}")
    @ApiOperation("创建订单")
    public Result createOrder (@PathVariable String courseId , HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderId = tOrderService.createOrder(courseId,memberId);
        return Result.ok().data("orderId",orderId);
    }

    @GetMapping("getOrderInfo/{orderNo}")
    @ApiOperation("通过id获取订单")
    public Result getOrderInfo (@PathVariable String orderNo) {
        QueryWrapper<TOrder> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        TOrder order = tOrderService.getOne(queryWrapper);
        return Result.ok().data("order",order);
    }

}

