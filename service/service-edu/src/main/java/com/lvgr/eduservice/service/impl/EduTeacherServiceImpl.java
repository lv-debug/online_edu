package com.lvgr.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvgr.eduservice.entity.EduTeacher;
import com.lvgr.eduservice.mapper.EduTeacherMapper;
import com.lvgr.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher) {
        QueryWrapper<EduTeacher> queryWrapperTeacher = new QueryWrapper<>();
        queryWrapperTeacher.orderByDesc("id");
        baseMapper.selectPage(pageTeacher, queryWrapperTeacher);
        List<EduTeacher> records = pageTeacher.getRecords();
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        long size = pageTeacher.getSize();
        long total = pageTeacher.getTotal();
        boolean hasNext = pageTeacher.hasNext();//下一页
        boolean hasPrevious = pageTeacher.hasPrevious();//上一页
        Map<String, Object> map = new HashMap<>();
        //map.put("records",pageTeacher.getRecords());
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }
}
