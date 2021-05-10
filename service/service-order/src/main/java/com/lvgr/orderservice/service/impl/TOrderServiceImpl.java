package com.lvgr.orderservice.service.impl;

import com.lvgr.orderservice.client.EduClient;
import com.lvgr.orderservice.client.UcenterClient;
import com.lvgr.orderservice.entity.TOrder;
import com.lvgr.orderservice.mapper.TOrderMapper;
import com.lvgr.orderservice.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvgr.orderservice.utils.OrderNoUtil;
import com.lvgr.utils.JwtUtils;
import com.lvgr.utils.ordervo.CourseWebVoOrder;
import com.lvgr.utils.ordervo.UcenterMemberOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lvgr
 * @since 2021-05-07
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        //通过远程调用获取用户信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseByOrder(courseId);
        //通过远程调用获取课程信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserByOrder(memberId);
        //创建order对象，向order对象里面设置数据
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        //返回订单号
        return order.getOrderNo();

    }
}
