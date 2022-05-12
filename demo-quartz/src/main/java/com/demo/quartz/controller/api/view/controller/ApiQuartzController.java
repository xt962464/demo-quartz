package com.demo.quartz.controller.api.view.controller;

import com.demo.quartz.config.MybatisPlus.group.AddGroup;
import com.demo.quartz.config.MybatisPlus.group.DefaultGroup;
import com.demo.quartz.config.MybatisPlus.group.UpdateGroup;
import com.alibaba.fastjson.JSONObject;
import com.demo.quartz.modules.quartz.model.dto.QuartzDTO;
import com.demo.quartz.modules.quartz.service.QuartzService;
import com.demo.quartz.modules.quartz.model.entity.QuartzEntity;
import com.demo.quartz.utils.ConvertUtils;
import com.demo.quartz.utils.page.PageData;
import com.demo.quartz.utils.response.data.ResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * Description: 【  ApiController控制器 】
 */
@RestController
@RequestMapping("/app/quartz")
public class ApiQuartzController {

    @Autowired
    private QuartzService quartzService;

    // ==================== 查询 ====================

    /**
     * Description: 【 分页条件查询 】
     *
     * @param queryParams
     */
    @GetMapping("page")
    public ResponseData<PageData<QuartzDTO>> pageGet(@RequestParam Map<String, Object> queryParams) {
        queryParams.put("status", 1);
        PageData<QuartzDTO> page = quartzService.page(queryParams);
        return new ResponseData<PageData<QuartzDTO>>().success(page);
    }

    /**
     * Description:【 列表查询 】
     */
    @GetMapping("list")
    public ResponseData<List<QuartzDTO>> listGet() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("status", 1);
        List<QuartzDTO> list = quartzService.list(queryParams);
        return new ResponseData<List<QuartzDTO>>().success(list);
    }

    /**
     * Description: 【 通过ID查询单一数据 】
     *
     * @param id
     */
    @GetMapping("{id}")
    public ResponseData<QuartzDTO> get(@PathVariable("id") Long id) {
        QuartzDTO data = quartzService.get(id);
        return new ResponseData<QuartzDTO>().success(data);
    }

    // ==================== 增 ====================

    /**
     * Description: 【 新增保存 】
     *
     * @param dto
     */
    @PostMapping
    public ResponseData save(@RequestBody QuartzDTO dto) throws Exception {
        quartzService.save(dto);
        return new ResponseData();
    }

    // ========================= 修改 ==============================

    /**
     * Description: 【 修改 】
     *
     * @param dto
     */
    @PutMapping
    public ResponseData update(@RequestBody QuartzDTO dto) throws Exception {
        quartzService.update(dto);
        return new ResponseData();
    }

    // ========================= 删除 ==============================

    /**
     * Description: 【 删除 】
     *
     * @param ids
     */
    @DeleteMapping
    public ResponseData delete(@RequestBody Long[] ids) {
        quartzService.deleteBatch(ids);

        return new ResponseData();
    }

    // ==================== 其他 ====================


}

