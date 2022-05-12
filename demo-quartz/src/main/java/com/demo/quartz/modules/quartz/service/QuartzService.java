package com.demo.quartz.modules.quartz.service;


import com.demo.quartz.config.MybatisPlus.service.BaseService;
import com.demo.quartz.modules.quartz.model.dto.QuartzDTO;
import com.demo.quartz.modules.quartz.model.entity.QuartzEntity;
import com.demo.quartz.utils.page.PageData;

import java.util.List;
import java.util.Map;

/**
 * Description: 【  Service接口 】
 */
public interface QuartzService extends BaseService<QuartzEntity> {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     */
    PageData<QuartzDTO> page(Map<String, Object> queryParams);

    /**
     * Description:【 条件查询 】
     *
     * @param queryParams 查询参数
     */
    List<QuartzDTO> list(Map<String, Object> queryParams);

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     */
    QuartzDTO get(Long id);

    // ==================== ADD、MODIFY、REMOVE ===================

    /**
     * Description:【 保存到数据库中 】
     *
     * @param dto 数据
     */
    boolean save(QuartzDTO dto);

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     */
    boolean update(QuartzDTO dto);

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * Description:【 通过ID数组,批量删除定时任务 】
     *
     * @param ids ID数组
     */
    boolean deleteBatch(Long[] ids);


    // ==================== 其他 ====================

}
