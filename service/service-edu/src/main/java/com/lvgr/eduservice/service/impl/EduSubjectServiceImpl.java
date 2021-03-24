package com.lvgr.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.eduservice.entity.EduSubject;
import com.lvgr.eduservice.entity.excel.SubjectData;
import com.lvgr.eduservice.listener.SubjectExcelListener;
import com.lvgr.eduservice.mapper.EduSubjectMapper;
import com.lvgr.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lvgr
 * @since 2021-03-23
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 添加课程分类
     * @param file
     */
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            //此处不能交给spring管理，SubjectExcelListener不能被管理，因为是我们手动new出来的。
            //所以在new SubjectExcelListener(eduSubjectService)把eduSubjectService构造方法传进来。
            //filter、监听器、拦截器,不能直接被spring容器管理，因为他们在web容器里面是平级的关系。
            EasyExcel.read(inputStream,SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Map<String,Object>> getAllsubject() {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id","0");
        List<EduSubject> list = this.list(queryWrapper);

        List<Map<String,Object>> listMap = new ArrayList<>();
        list.stream().forEach(eduSubject ->{
            Map<String,Object> map = new HashMap();
            map.put("id",eduSubject.getId());
            map.put("label",eduSubject.getTitle());

            QueryWrapper queryWrapperSon = new QueryWrapper();
            queryWrapperSon.eq("parent_id",eduSubject.getId());
            List<EduSubject> listSon = this.list(queryWrapperSon);

            List<Map<String,Object>> listMapSon = new ArrayList<>();
            listSon.stream().forEach(eduSubjectSon ->{
                Map<String,Object> mapSon = new HashMap();
                mapSon.put("id",eduSubjectSon.getId());
                mapSon.put("label",eduSubjectSon.getTitle());
                listMapSon.add(mapSon);
            });

            map.put("children",listMapSon);
            listMap.add(map);
        });
        return listMap;
    }
}
