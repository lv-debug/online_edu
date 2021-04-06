package com.lvgr.eduservice.service;

import com.lvgr.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean delEduVideoById(String eduVideoId);

    void removeByCourseId(String courseId);
}
