package com.lvgr.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 视频上传
 */
public interface VodService {
    String uploadAliyVideo(MultipartFile file);
}
