package com.demo.quartz.modules.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.demo.quartz.modules.quartz.model.entity.QuartzEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description: 【  数据库操作Mapper 】
 */
@Mapper
public interface QuartzMapper extends BaseMapper<QuartzEntity> {

    // ==================== 查询 ====================

    // ==================== ADD、MODIFY、REMOVE ===================


    // ==================== 其他 ====================

}
