package com.lvgr.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadFileStreamRequest;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadFileStreamResponse;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.lvgr.vod.service.VodService;
import com.lvgr.vod.utils.ConstantVodUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 视频上传实现类
 */

@Service
public class VodServiceImpl implements VodService {

    /**
     * 上传至阿里云
     * @param file
     * @return
     */
    @Override
    public String uploadAliyVideo(MultipartFile file) {

        //title:上传显示到阿里云的名称
        //fileName:文件的原始名称
        //inputStream:文件输入流

        InputStream inputStream = null;
        try {
            String accessKeyId = ConstantVodUtils.KEY_ID;
            String accessKeySecret = ConstantVodUtils.KEY_SECRET;
            String title = file.getName();
            String fileName = file.getOriginalFilename();
            inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
            return response.getVideoId();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
