package com.lvgr.eduservice.controller;

import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


/**
 * @author lvgr
 * @date 2021/3/13 15:34
 * @desc :测试模拟登录
 * @CrossOrigin 解决跨域问题
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
//@CrossOrigin
@RestController
@RequestMapping("/eduservice/user")
@Api(description = "登录")
public class EduLoginController {

    @ApiOperation("登录")
    @PostMapping("login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public Result info(){
        return Result.ok().data("roles","[roles]").data("name","admin").data("avatar","https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=tupian&step_word=&hs=0&pn=10&spn=0&di=116600&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=3387716082%2C1768697998&os=1163914097%2C393605918&simid=3488817304%2C283956059&adpicid=0&lpn=0&ln=1635&fr=&fmq=1615621231723_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Fa2.att.hudong.com%2F42%2F31%2F01300001320894132989315766618.jpg%26refer%3Dhttp%3A%2F%2Fa2.att.hudong.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1618213242%26t%3Dcd970371bfe30c53963a5d3942cbf433&fromurl=ippr_z2C%24qAzdH3FAzdH3Fp7rtwg_z%26e3Bkwthj_z%26e3Bv54AzdH3Ftrw1AzdH3Fwd_9d_n8_a8naaaa8ndabl98ndlbln8c0mmm8b_3r2_z%26e3Bip4s&gsm=b&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
    }
}
