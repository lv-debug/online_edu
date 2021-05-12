package com.lvgr.orderservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.lvgr.orderservice.entity.TOrder;
import com.lvgr.orderservice.entity.TPayLog;
import com.lvgr.orderservice.mapper.TPayLogMapper;
import com.lvgr.orderservice.service.TOrderService;
import com.lvgr.orderservice.service.TPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvgr.orderservice.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author lvgr
 * @since 2021-05-07
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {

    @Autowired
    private TOrderService tOrderService;
    @Override
    public Map createRWCode(String orderNo) {
        try {
            //根据订单号查询订单信息
            QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_no",orderNo);
            TOrder order = tOrderService.getOne(queryWrapper);

            //设置二维码的参数
            Map m = new HashMap();
            m.put("appid", "wx74862e0dfcf69954");//微信id，关键字段
            m.put("mch_id", "1558950191");//商户号，唯一值
            m.put("nonce_str", WXPayUtil.generateNonceStr());//生成随机的字符串，以使得生成的二维码都不一样
            m.put("body", order.getCourseTitle());//二维码的名称：课程标题
            m.put("out_trade_no", orderNo);//订单号
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");//订单的价格
            m.put("spbill_create_ip", "127.0.0.1");//支付的ip地址
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify");
            m.put("trade_type", "NATIVE");

            //发送请求，传递参数（xml格式），微信支付的固定地址
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //client设置参数
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);//支持https访问
            client.post();

            //得到发送请求返回的结果。
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            //封装返回结果集
            Map map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));//返回二维码操作的状态码
            map.put("code_url", resultMap.get("code_url"));//二维码地址
            //微信支付二维码2小时过期，可采取2小时未支付取消订单
            //redisTemplate.opsForValue().set(orderNo, map, 120, TimeUnit.MINUTES);
            return map;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map queryRWCode(String orderNo) {
        try {
            //1、封装参数
            Map m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2 发送httpclient
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            //3 得到请求返回内容
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //6、转成Map再返回
            return resultMap;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrderStatus(Map<String,String> map) {
        //获取订单号
        String orderNo = map.get("out_trade_no");
        //根据订单号查询订单信息
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        TOrder order = tOrderService.getOne(queryWrapper);

        //更新订单表里面的订单状态
        if (order.getStatus().intValue() == 1) {
            return;
        }
        order.setStatus(1);
        tOrderService.updateById(order);

        //向支付表添加支付信息
        TPayLog payLog = new TPayLog();
        payLog.setOrderNo(orderNo);  //订单号
        payLog.setPayTime(new Date()); //订单完成时间
        payLog.setPayType(1);//支付类型 1微信
        payLog.setTotalFee(order.getTotalFee());//总金额(分)

        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id")); //流水号
        payLog.setAttr(JSONObject.toJSONString(map));//支付的其他属性，全部先保存进来

        baseMapper.insert(payLog);
    }
}
