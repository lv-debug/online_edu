package com.lvgr.ossservice.controller;

import com.lvgr.ossservice.service.OssService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lvgr
 * @date 2021/3/19 23:00
 * @desc:文件上传实现类
 *
 * @CrossOrigin:使用nginx做反向代理需要该注解,目前使用的时gateway，在配置文件已经配置了跨域，所以不需要该注解
 */
@RestController
@RequestMapping("/eduoss/fileOss")
//@CrossOrigin
@Api(description = "上传")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping
    public Result upload(MultipartFile file){

        String url = null;
        try {
            url = ossService.uploadFileAvatar(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok().data("url",url);
    }
}
