package com.lvgr.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.eduservice.entity.EduTeacher;
import com.lvgr.eduservice.mapper.EduTeacherMapper;
import com.lvgr.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author lvgr
 * @since 2020-07-29
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Cacheable(value="eduTeacher",key="'selectEduTeacher'")
    @Override
    public List<EduTeacher> eduTeacherList() {
        QueryWrapper<EduTeacher> queryWrapperTeacher = new QueryWrapper<>();
        queryWrapperTeacher.orderByDesc("id");
        queryWrapperTeacher.last("limit 4");
        return baseMapper.selectList(queryWrapperTeacher);
    }
}
