package com.lvgr.eduservice.client;

import com.lvgr.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lvgr
 * @desc 删除阿里云视频调用工具实现类：出错后才会执行下面定义的方法
 * @date 2021/4/13 22:52
 */

@Component
public class VodClientErrorImpl implements VodClient {
    @Override
    public Result removeAliyVod(String videoId) {
        return Result.error().message("删除视频出错");
    }

    @Override
    public Result deleteBatch(List<String> videoIds) {
        return Result.error().message("批量删除视频出错");
    }
}
