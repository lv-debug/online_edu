package com.lvgr.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lvgr
 * @date 2021/3/19 23:00
 * @desc
 */

public interface OssService{
    String uploadFileAvatar(MultipartFile file) throws IOException;
}
