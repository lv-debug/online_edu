package com.lvgr.cmsservice.service;

import com.lvgr.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.boot.Banner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author lvgr
 * @since 2021-04-17
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllBanner();
}
