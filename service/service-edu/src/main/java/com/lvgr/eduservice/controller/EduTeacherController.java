package com.lvgr.eduservice.controller;


import com.lvgr.eduservice.entity.EduTeacher;
import com.lvgr.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2020-07-29
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询讲师表的所有数据
     */
    @GetMapping("findAllTeacher")
    public List<EduTeacher> findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    /**
     * 逻辑删除讲师
     */
    @DeleteMapping("{id}")
    public boolean deleteTeacher(@PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        return b;
    }


}

