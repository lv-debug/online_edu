package com.lvgr.eduservice.controller;


import com.alibaba.fastjson.JSON;
import com.lvgr.eduservice.service.EduSubjectService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2021-03-23
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-subject")
@Api(description = "科目管理")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {

        eduSubjectService.saveSubject(file,eduSubjectService);
        return Result.ok();
    }

    @ApiOperation(value = "获取所有课程分类")
    @GetMapping("getAllsubject")
    public Result getAllsubject(){

        List<Map<String,Object>> allsubject = eduSubjectService.getAllsubject();
        Object jsonObject = JSON.toJSON(allsubject);
        return Result.ok().data("jsonObject",jsonObject);
    }


}

