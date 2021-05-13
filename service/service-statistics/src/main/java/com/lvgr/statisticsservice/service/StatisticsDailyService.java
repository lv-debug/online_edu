package com.lvgr.statisticsservice.service;

import com.lvgr.statisticsservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author lvgr
 * @since 2021-05-12
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void countRegisterCount(String day);
}
