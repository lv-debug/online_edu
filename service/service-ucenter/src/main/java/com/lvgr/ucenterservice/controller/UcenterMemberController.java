package com.lvgr.ucenterservice.controller;


import com.lvgr.ucenterservice.entity.UcenterMember;
import com.lvgr.ucenterservice.entity.vo.RegisterVo;
import com.lvgr.ucenterservice.service.UcenterMemberService;
import com.lvgr.utils.JwtUtils;
import com.lvgr.utils.Result;
import com.lvgr.utils.ordervo.UcenterMemberOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 会员用户注册中心管理
 *
 * @author lvgr
 * @since 2021-04-22
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
@RestController
@RequestMapping("/ucenterservice/ucenter-member")
//@CrossOrigin
@Api(description = "会员用户注册中心管理")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @PostMapping("login")
    @ApiOperation("前端登录方法")
    public Result loginUser(@RequestBody UcenterMember ucenterMember) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = ucenterMemberService.loginUser(ucenterMember);
        return Result.ok().data("token",token);

    }

    @PostMapping("register")
    @ApiOperation("注册")
    public Result register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return Result.ok();
    }

    @GetMapping("getMemberInfo")
    @ApiOperation("根据token获取用户")
    public Result getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = ucenterMemberService.getById(memberId);
        return Result.ok().data("userInfo",member);
    }

    @PostMapping("getUserByOrder/{id}")
    @ApiOperation("根据用户id获取用户")
    public UcenterMemberOrder getUserByOrder(@PathVariable String id){
        UcenterMember ucenterMember = ucenterMemberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    @GetMapping("countRegister/{day}")
    @ApiOperation("根据token获取用户")
    public Result countRegister(@PathVariable String day){
        //查询数据库根据用户id获取用户信息
        int count = ucenterMemberService.countRegister(day);
        return Result.ok().data("countRegister",count);
    }

}

