CREATE TABLE `demo-quartz`.`quartz`
(
    `id`           bigint NOT NULL COMMENT 'id',
    `created_time` datetime NULL COMMENT '创建时间',
    `updated_time` datetime NULL COMMENT '更新时间',
    `name`         varchar(255) NULL COMMENT '任务名',
    `description`  varchar(255) NULL COMMENT '描述',
    `class_path`   varchar(255) NULL COMMENT '类名',
    `type`         varchar(255) NULL COMMENT '类型',
    `h`            int NULL COMMENT '时',
    `m`            int NULL COMMENT '分',
    `period_type`  varchar(255) NULL COMMENT '周期类型',
    `period`       int NULL COMMENT '周期数',
    `cron`         varchar(255) NULL COMMENT 'cron表达式',
    PRIMARY KEY (`id`)
);