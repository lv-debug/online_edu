package com.lvgr.eduservice.service;

import com.lvgr.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvgr.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean delChapterById(String chapterId);

    void removeByCourseId(String courseId);
}
