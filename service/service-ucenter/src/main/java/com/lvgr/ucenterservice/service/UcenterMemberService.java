package com.lvgr.ucenterservice.service;

import com.lvgr.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvgr.ucenterservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lvgr
 * @since 2021-04-22
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String loginUser(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);

    int countRegister(String day);
}
