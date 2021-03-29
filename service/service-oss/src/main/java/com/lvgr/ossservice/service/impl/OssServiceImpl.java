package com.lvgr.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lvgr.ossservice.service.OssService;
import com.lvgr.ossservice.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author lvgr
 * @date 2021/3/19 23:02
 * @desc：实现文件（头像）上传
 */

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取上传的文件流。
            InputStream inputStream = file.getInputStream();
            //文件按照日期分类
            //获取当前日期
            String currdatePath = new DateTime().toString("yyyy/MM/dd");


            // 填写Bucket名称和文件名称。
            String filename = file.getOriginalFilename();
            //在文件名称里面添加唯一的随机值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            ossClient.putObject(bucketName, currdatePath+"/"+uuid+filename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //把阿里云的文件路径拼接起来
            String url = "https://"+bucketName+"."+endpoint+"/"+currdatePath+"/"+uuid+filename;
            return url;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
