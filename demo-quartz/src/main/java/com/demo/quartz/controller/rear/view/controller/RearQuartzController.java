package com.demo.quartz.controller.rear.view.controller;

import com.demo.quartz.modules.quartz.model.dto.QuartzDTO;
import com.demo.quartz.modules.quartz.service.QuartzService;
import com.demo.quartz.utils.page.PageData;
import com.demo.quartz.utils.response.data.ResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description: 【  后台Controller控制器 】
 */
@RestController
@RequestMapping("/admin/quartz/")
public class RearQuartzController {

    @Autowired
    private QuartzService quartzService;

    // ==================== 查询 ====================

    /**
     * Description: 【 分页条件查询 】
     */
    @GetMapping("page")
    public ResponseData<PageData<QuartzDTO>> pageGet(@RequestParam Map<String, Object> queryParams) {
        PageData<QuartzDTO> page = quartzService.page(queryParams);

        return new ResponseData<PageData<QuartzDTO>>().success(page);
    }

    /**
     * Description:【 列表条件查询 】
     *
     * @param queryParams
     */
    @GetMapping("list")
    public ResponseData<List<QuartzDTO>> listGet(@RequestParam Map<String, Object> queryParams) {
        List<QuartzDTO> data = quartzService.list(queryParams);

        return new ResponseData<List<QuartzDTO>>().success(data);
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

    // ==================== 增加 ====================

    /**
     * Description: 【 新增保存 】
     *
     * @param dto
     */
    @PostMapping("save")
    public ResponseData save(@RequestBody QuartzDTO dto) throws Exception {
        quartzService.save(dto);
        return new ResponseData();
    }

    // =================== 修改 ============================

    /**
     * Description: 【 修改 】
     *
     * @param dto
     */
    @PutMapping("update")
    public ResponseData update(@RequestBody QuartzDTO dto) throws Exception {
        quartzService.update(dto);
        return new ResponseData();
    }


    // ============================= 删除 ===============================

    /**
     * Description:【 通过ID删除单条数据 】
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id") Long id) {
        quartzService.delete(id);
        return new ResponseData();
    }

    /**
     * Description: 【 批量删除 】
     *
     * @param ids
     */
    @DeleteMapping("delete")
    public ResponseData delete(@RequestBody Long[] ids) {
        //效验数据
        // AssertUtil.isNotNull(id, "参数不能为空");

        quartzService.deleteBatch(ids);

        return new ResponseData();
    }

    @GetMapping("runOne/{id}")
    public ResponseData<?> runOne(@PathVariable("id") Long id){

        return new ResponseData<>().success();
    }


    // ======================== 其他 ==============================

}
