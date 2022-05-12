package com.demo.quartz.config.MybatisPlus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BaseEntity<T extends Model<T>> extends AbstractBasicEntity<T> {

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @TableField(
            value = "modify_time",
            fill = FieldFill.INSERT_UPDATE
    )
    private Date modifyTime;


    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @TableField(
            value = "created_time",
            fill = FieldFill.INSERT
    )
    private Date createdTime;

    public BaseEntity() {

    }


    public Date getModifyTime() {
        return this.modifyTime;
    }
    public void setModifyTime(final Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
