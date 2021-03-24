package com.lvgr.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.edubase.exceptionhandle.EduException;
import com.lvgr.eduservice.entity.EduSubject;
import com.lvgr.eduservice.entity.excel.SubjectData;
import com.lvgr.eduservice.service.EduSubjectService;

import java.util.Map;

/**
 * @author lvgr
 * @date 2021/3/23 16:10
 * @desc
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //SubjectExcelListener不能交给spring管理，需要自己new，不能注入其他对象
    //不能实现数据库操作。
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    //一行一行去读取excle内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        if(subjectData == null){
            throw new EduException(20001,"文件数据为空");
        }
        //判断一级分类不能重复添加
        EduSubject oneEduSubject = this.ifExistOneEduSubject(eduSubjectService, subjectData.getOneSubjectName());
        if(oneEduSubject == null){
            oneEduSubject = new EduSubject();
            oneEduSubject.setTitle(subjectData.getOneSubjectName());
            oneEduSubject.setParentId("0");
            eduSubjectService.save(oneEduSubject);

        }

        //获取一级分类id值
        String pid = oneEduSubject.getId();

        //判断二级分类不能重复添加
        EduSubject twoEduSubject = this.ifExistTwoEduSubject(eduSubjectService, subjectData.getTwoSubjectName(),pid);
        if(twoEduSubject == null){
            twoEduSubject = new EduSubject();
            twoEduSubject.setTitle(subjectData.getTwoSubjectName());
            twoEduSubject.setParentId(pid);
            eduSubjectService.save(twoEduSubject);

        }


    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    private EduSubject ifExistOneEduSubject(EduSubjectService eduSubjectService,String name){

        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",0);
        EduSubject one = eduSubjectService.getOne(queryWrapper);
        return one;
    }


    private EduSubject ifExistTwoEduSubject(EduSubjectService eduSubjectService,String name,String pid){

        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        EduSubject two = eduSubjectService.getOne(queryWrapper);
        return two;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
