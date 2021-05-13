package com.lvgr.ucenterservice.mapper;

import com.lvgr.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author lvgr
 * @since 2021-04-22
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer countRegister(@Param("createDay") String day);
}
