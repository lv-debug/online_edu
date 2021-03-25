package com.lvgr.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvgr
 * @date 2021/3/25 16:21
 * @desc
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    List<VideoVo> children = new ArrayList<>();
}
