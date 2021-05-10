package com.lvgr.orderservice.service;

import com.lvgr.orderservice.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author lvgr
 * @since 2021-05-07
 */
public interface TOrderService extends IService<TOrder> {

    String createOrder(String courseId, String memberId);
}
