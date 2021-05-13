package com.lvgr.ucenterservice.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.edubase.exceptionhandle.EduException;
import com.lvgr.ucenterservice.entity.UcenterMember;
import com.lvgr.ucenterservice.entity.vo.RegisterVo;
import com.lvgr.ucenterservice.mapper.UcenterMemberMapper;
import com.lvgr.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvgr.ucenterservice.utils.MD5;
import com.lvgr.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lvgr
 * @since 2021-04-22
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String loginUser(UcenterMember ucenterMember) {
        //获取登录手机号和密码
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();

        //手机号和密码非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new EduException(20001,"登录失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if(mobileMember == null) {//没有这个手机号
            throw new EduException(20001,"登录失败");
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new EduException(20001,"登录失败");
        }

        //判断用户是否禁用
        if(mobileMember.getIsDisabled()) {
            throw new EduException(20001,"登录失败");
        }

        //登录成功
        //生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {

        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        //非空判断
        if(StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new EduException(20001,"注册失败");
        }

        //判断验证码
        //根据手机号获取到redis的验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)){
            throw new EduException(20001,"注册失败,验证码验证失败");
        }

        //判断手机号是否重复，重复就不添加。
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer ucenterMemberCount = baseMapper.selectCount(queryWrapper);
        if(ucenterMemberCount > 0){
            throw new EduException(20001,"注册失败,手机号重复");
        }

        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setNickname(nickname);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);//用户不禁用
        ucenterMember.setAvatar("https://edu-lvgr.oss-cn-chengdu.aliyuncs.com/2021/03/22/5e95dfb600fa4109b43ac3dc20698027file.png");
        baseMapper.insert(ucenterMember);

    }

    @Override
    public int countRegister(String day) {
        return baseMapper.countRegister(day);
    }
}
