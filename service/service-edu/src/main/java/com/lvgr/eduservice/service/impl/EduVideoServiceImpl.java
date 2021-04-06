package com.lvgr.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.edubase.exceptionhandle.EduException;
import com.lvgr.eduservice.entity.EduVideo;
import com.lvgr.eduservice.mapper.EduVideoMapper;
import com.lvgr.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author lvgr
 * @since 2021-03-24
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public boolean delEduVideoById(String eduVideoId) {
//        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("chapter_id",chapterId);
//        int size = eduVideoService.count(queryWrapper);
//        if(size > 0) {
//            throw new EduException(20001,"删除的章节包含小节，禁止删除");
//        }else {
//            int i = baseMapper.deleteById(chapterId);
//            return i > 0;
//        }
        return true;
    }

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }
}
