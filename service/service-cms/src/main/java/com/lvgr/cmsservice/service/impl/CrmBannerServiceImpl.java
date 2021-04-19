package com.lvgr.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvgr.cmsservice.entity.CrmBanner;
import com.lvgr.cmsservice.mapper.CrmBannerMapper;
import com.lvgr.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.boot.Banner;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author lvgr
 * @since 2021-04-17
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(value="banner",key="'selectAllBanner'")
    @Override
    public List<CrmBanner> selectAllBanner() {
        //根据id降序
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 2");
        List<CrmBanner> crmBanners = baseMapper.selectList(queryWrapper);
        return crmBanners;
    }
}
