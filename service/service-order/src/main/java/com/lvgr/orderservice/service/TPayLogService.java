package com.lvgr.orderservice.service;

import com.lvgr.orderservice.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author lvgr
 * @since 2021-05-07
 */
public interface TPayLogService extends IService<TPayLog> {

    Map createRWCode(String orderNo);

    Map queryRWCode(String orderNo);

    void updateOrderStatus(Map<String,String> map);
}
