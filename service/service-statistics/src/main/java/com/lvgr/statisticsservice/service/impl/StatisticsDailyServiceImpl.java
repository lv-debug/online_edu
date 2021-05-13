package com.lvgr.statisticsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.statisticsservice.client.UcenterClient;
import com.lvgr.statisticsservice.entity.StatisticsDaily;
import com.lvgr.statisticsservice.mapper.StatisticsDailyMapper;
import com.lvgr.statisticsservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvgr.utils.Result;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author lvgr
 * @since 2021-05-12
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void countRegisterCount(String day) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated",day);
        baseMapper.delete(queryWrapper);

        Result result = ucenterClient.countRegister(day);
        Object countRegister = result.getData().get("countRegister");
        Integer countRegisterCount = (Integer) countRegister;

        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(countRegisterCount);
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setDateCalculated(day);
        baseMapper.insert(statisticsDaily);
    }
}
