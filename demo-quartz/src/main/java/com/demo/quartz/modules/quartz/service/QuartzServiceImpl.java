package com.demo.quartz.modules.quartz.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;


import com.demo.quartz.config.MybatisPlus.service.BaseServiceImpl;
import com.demo.quartz.modules.quartz.mapper.QuartzMapper;
import com.demo.quartz.modules.quartz.model.dto.QuartzDTO;
import com.demo.quartz.modules.quartz.model.entity.QuartzEntity;
import com.demo.quartz.utils.ConvertUtils;
import com.demo.quartz.utils.page.PageData;
import com.demo.quartz.utils.page.PageUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description: 【  Service接口实现类 】
 */
@Service
public class QuartzServiceImpl extends BaseServiceImpl<QuartzMapper, QuartzEntity> implements QuartzService {

    // ==================== 查询 ====================

    /**
     * Description:【 分页条件查询 】
     *
     * @param queryParams 查询参数
     */
    @Override
    public PageData<QuartzDTO> page(Map<String, Object> queryParams) {
        PageUtils.initPage(queryParams);

        IPage<QuartzEntity> page = baseMapper.selectPage(
                PageUtils.initPage(queryParams),
                getWrapper(queryParams).orderByDesc(QuartzEntity::getCreateTime)
        );
        return getPageData(page, QuartzDTO.class);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams Map参数
     */
    @Override
    public List<QuartzDTO> list(Map<String, Object> queryParams) {
        List<QuartzEntity> entityList = baseMapper.selectList(getWrapper(queryParams));

        return ConvertUtils.sourceToTarget(entityList, QuartzDTO.class);
    }

    /**
     * Description:【 通过ID获取单条数据 】
     *
     * @param id
     */
    @Override
    public QuartzDTO get(Long id) {
        return ConvertUtils.sourceToTarget(baseMapper.selectById(id), QuartzDTO.class);
    }

    // ==================== 增、改、删====================

    /**
     * Description:【 保存到数据库中 】
     *
     * @param dto 数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(QuartzDTO dto) {
        QuartzEntity entity = ConvertUtils.sourceToTarget(dto, QuartzEntity.class);

        return save(entity);
    }

    /**
     * Description:【 修改数据 】
     *
     * @param dto 数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(QuartzDTO dto) {
        QuartzEntity entity = ConvertUtils.sourceToTarget(dto, QuartzEntity.class);


        return updateById(entity);
    }

    /**
     * Description:【 通过ID删除数据 】
     *
     * @param id ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {


        //删除
        return removeById(id);
    }

    /**
     * Description:【 通过ID数组,批量删除定时任务 】
     *
     * @param ids ID数组
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Long[] ids) {


        //删除数据
        return removeByIds(Arrays.asList(ids));
    }


    // ==================== 其他 ====================


    /**
     * Description: 【 初始化查询条件 】
     *
     * @param queryParams
     */
    private LambdaQueryWrapper<QuartzEntity> getWrapper(Map<String, Object> queryParams) {
        LambdaQueryWrapper<QuartzEntity> wrapper = new LambdaQueryWrapper<>();

        //时间查询
        JSONObject dateTime = JSON.parseObject(MapUtils.getString(queryParams, "dateTime"));
        if (dateTime != null && dateTime.size() > 0) {
            String startTime = dateTime.getString("startTime");
            String endTime = dateTime.getString("endTime");
            wrapper.ge(StringUtils.isNotBlank(startTime), QuartzEntity::getCreateTime, startTime);
            wrapper.le(StringUtils.isNotBlank(endTime), QuartzEntity::getCreateTime, endTime);
        }

        // 任务名
        String name = MapUtils.getString(queryParams, "name", "");
        wrapper.and(StringUtils.isNotBlank(name.trim()), s -> s.like(QuartzEntity::getName, name.trim()));

        // 关键字
        //String searchWord = MapUtils.getString(queryParams, "searchWord", "");
        //wrapper.and(StringUtils.isNotBlank(searchWord.trim()), s -> s.like(QuartzEntity::getName, searchWord.trim()));

        // 状态
        //Integer searchStatus = MapUtils.getInteger(queryParams, "status", null);
        //wrapper.eq(searchStatus != null, QuartzEntity::getStatus, searchStatus);

        // others

        return wrapper;
    }


}
