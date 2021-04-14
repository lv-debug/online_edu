package com.lvgr.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 视频上传
 */
public interface VodService {
    String uploadAliyVideo(MultipartFile file);

    void deleteBatch(List<String> videoIds);
}
