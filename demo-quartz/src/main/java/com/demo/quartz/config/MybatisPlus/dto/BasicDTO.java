package com.demo.quartz.config.MybatisPlus.dto;

import com.demo.quartz.config.MybatisPlus.group.AddGroup;
import com.demo.quartz.config.MybatisPlus.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public abstract class BasicDTO implements Serializable {

    @Null(
            message = "{id.null}",
            groups = {AddGroup.class}
    )
    @NotNull( message = "{id.require}",groups = {UpdateGroup.class})
    private Long id;

    @JsonProperty(
            access = Access.READ_ONLY
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createdTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date modifyTime;

    public BasicDTO() {}

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }
    @JsonProperty(
            access = Access.READ_ONLY
    )
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
