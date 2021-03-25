package com.lvgr.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.eduservice.entity.EduChapter;
import com.lvgr.eduservice.entity.EduVideo;
import com.lvgr.eduservice.entity.chapter.ChapterVo;
import com.lvgr.eduservice.entity.chapter.VideoVo;
import com.lvgr.eduservice.mapper.EduChapterMapper;
import com.lvgr.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvgr.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapterList = this.list(queryWrapper);

        List<ChapterVo> chapterVos = new ArrayList<>();
        eduChapterList.stream().forEach(eduChapter -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);

            QueryWrapper<EduVideo> queryWrapperVideo = new QueryWrapper<>();
            queryWrapperVideo.eq("chapter_id",eduChapter.getId());
            List<EduVideo> eduVidelList = eduVideoService.list(queryWrapperVideo);
            List<VideoVo> videoVos = new ArrayList<>();
            eduVidelList.stream().forEach(eduVideo -> {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(eduVideo,videoVo);
                videoVos.add(videoVo);
            });
            chapterVo.setChildren(videoVos);
            chapterVos.add(chapterVo);
        });
        return chapterVos;
    }
}
