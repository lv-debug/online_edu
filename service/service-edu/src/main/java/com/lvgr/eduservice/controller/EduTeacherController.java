package com.lvgr.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvgr.edubase.exceptionhandle.ClobalExceptionHandle;
import com.lvgr.edubase.exceptionhandle.EduException;
import com.lvgr.eduservice.entity.EduTeacher;
import com.lvgr.eduservice.entity.vo.TecherQuery;
import com.lvgr.eduservice.service.EduTeacherService;
import com.lvgr.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lvgr
 * @since 2020-07-29
 */

@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-teacher")
@Api(description = "讲师管理")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("查询讲师列表")
    @GetMapping("findAllTeacher")
    public Result findAllTeacher(){

//        try {
//            int sad =10/0;
//        }catch (Exception e){
//            throw new EduException(EduException.ERROR,"被除数问题");
//        }
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("list",list);
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result deleteTeacher(@PathVariable @ApiParam(name = "id",value = "讲师id",required = true) String id) {
        boolean b = eduTeacherService.removeById(id);
        if(b){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @ApiOperation("分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable @ApiParam(name = "current",value = "当前页",required = true) long current,
                                  @PathVariable @ApiParam(name = "limit",value = "当前页总数",required = true) long limit) {

        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        eduTeacherService.page(pageTeacher, null);

        Map map = new HashMap();
        map.put("total",pageTeacher.getTotal());
        map.put("records",pageTeacher.getRecords());

        return Result.ok().data(map);
    }


    @ApiOperation("分页带其他查询讲师")
    @PostMapping("pageTeacherQuery/{current}/{limit}")
    public Result pageTeacherQuery(@PathVariable @ApiParam(name = "current",value = "当前页",required = true) long current,
                                   @PathVariable @ApiParam(name = "limit",value = "当前页总数",required = true) long limit,
                                   @RequestBody(required = false) TecherQuery techerQuery) {

        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        String name = techerQuery.getName();
        Integer level = techerQuery.getLevel();
        String begin = techerQuery.getBegin();
        String end = techerQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }

        queryWrapper.orderByDesc("gmt_create");

        eduTeacherService.page(pageTeacher, queryWrapper);

        Map map = new HashMap();
        map.put("total",pageTeacher.getTotal());
        map.put("rows",pageTeacher.getRecords());

        return Result.ok().data(map);
    }

    @ApiOperation("新增讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean isAddTeacher = eduTeacherService.save(eduTeacher);
        if(isAddTeacher){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public Result getTeacherById(@PathVariable @ApiParam(name = "id",value = "讲师id",required = true) String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.ok().data("resTeacher",eduTeacher);
    }

    @ApiOperation("修改讲师")
    @PostMapping("updTeacher")
    public Result updTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean isAddTeacher = eduTeacherService.updateById(eduTeacher);
        if(isAddTeacher){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

}

