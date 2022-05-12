package com.demo.quartz.modules.quartz.model.dto;

import com.demo.quartz.config.MybatisPlus.dto.BasicDTO;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description: 【  DTO 】
 */
@Data
public class QuartzDTO extends BasicDTO {

    // ==================== 字段名 ====================

    /**
     * ID
     */
    private Long id;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date updatedTime;

    /**
     * 任务名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 类名
     */
    private String classPath;

    /**
     * 类型
     */
    private String type;

    /**
     * 时
     */
    private Integer h;

    /**
     * 分
     */
    private Integer m;

    /**
     * 周期类型
     */
    private String periodType;

    /**
     * 周期数
     */
    private Integer period;

    /**
     * CRON表达式
     */
    private String cron;

    // ==================== 非持久 ====================


}
