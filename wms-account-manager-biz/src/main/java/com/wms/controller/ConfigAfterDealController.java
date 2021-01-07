package com.wms.controller;

import com.wms.api.config.ConfigInWarehouseVo;
import com.xac.core.util.BeanListUtil;
import com.wms.model.entity.ConfigAfterDealEntity;
import com.wms.service.ConfigAfterDealService;
import com.wms.api.config.ConfigAfterDealQueryParam;
import com.wms.api.config.ConfigAfterDealVo;
import com.wms.model.bo.config.ConfigAfterDealBo;
import com.xac.core.api.ApiResult;
import com.xac.core.api.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;


import javax.validation.Valid;

import com.xac.core.api.Paging;

import java.util.List;

/**
 * <pre>
 * 后处理配置 前端控制器
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Slf4j
@RestController
@RequestMapping("/config")
@Api("后处理配置 API")
public class ConfigAfterDealController extends BaseController {

    @Autowired
    private ConfigAfterDealService configAfterDealService;

    /**
     * 添加后处理配置
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加ConfigAfterDeal对象", notes = "添加后处理配置", response = ApiResult.class)
    public ApiResult<Boolean> addConfigAfterDeal(@Valid @RequestBody ConfigAfterDealVo configAfterDeal) throws Exception
    {
         ConfigAfterDealBo bo = new ConfigAfterDealBo();
        BeanUtils.copyProperties(configAfterDeal,bo);

        boolean flag = configAfterDealService.saveConfigAfterDeal(bo);
        return ApiResult.result(flag);
    }

    /**
     * 修改后处理配置
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改ConfigAfterDeal对象", notes = "修改后处理配置", response = ApiResult.class)
    public ApiResult<Boolean> updateConfigAfterDeal(@Valid @RequestBody ConfigAfterDealVo configAfterDeal) throws Exception
    {
        ConfigAfterDealBo bo = new ConfigAfterDealBo();
        BeanUtils.copyProperties(configAfterDeal,bo);

        boolean flag = configAfterDealService.updateConfigAfterDeal(bo);
        return ApiResult.result(flag);
    }

    /**
     * 删除后处理配置
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除ConfigAfterDeal对象", notes = "删除后处理配置", response = ApiResult.class)
    public ApiResult<Boolean> deleteConfigAfterDeal(@PathVariable("id") Long id) throws Exception {
        boolean flag = configAfterDealService.deleteConfigAfterDeal(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取后处理配置
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取ConfigAfterDeal对象详情", notes = "查看后处理配置", response = ConfigAfterDealVo.class)
    public ApiResult<ConfigAfterDealVo> getConfigAfterDeal(@PathVariable("id") Long id) throws Exception
    {
        ConfigAfterDealBo configAfterDealBo = configAfterDealService.getConfigAfterDealById(id);
        ConfigAfterDealVo queryVo = null;
        if (configAfterDealBo != null) {
            queryVo = new ConfigAfterDealVo();
            BeanUtils.copyProperties(configAfterDealBo , queryVo);
        }
        return ApiResult.ok(queryVo);
    }

    /**
     * 后处理配置分页列表
     */
    @PostMapping("/pagelist")
    @ApiOperation(value = "获取ConfigAfterDeal分页列表", notes = "后处理配置分页列表", response = ConfigAfterDealVo.class)
    public ApiResult<Paging<ConfigAfterDealVo>> getConfigAfterDealPageList(@Valid @RequestBody ConfigAfterDealQueryParam configAfterDealQueryParam) throws Exception
    {
        Paging<ConfigAfterDealBo> paging = configAfterDealService.getConfigAfterDealPageList(configAfterDealQueryParam);
        Paging<ConfigAfterDealVo> resultPage = new Paging<>();
        resultPage.setTotal(paging.getTotal());
        resultPage.setRecords(BeanListUtil.copyListProperties(paging.getRecords(), ConfigAfterDealVo.class));
        return ApiResult.ok(resultPage);
    }

    /**
     * 根据库房查询入库配置列表
     */
    @PostMapping("/query/afterDeal/queryConfigList")
    @ApiOperation(value = "根据库房查询入库配置列表", notes = "根据库房查询入库配置列表", response = ConfigInWarehouseVo.class)
    public ApiResult<List<ConfigAfterDealBo>> queryConfigInWarehouseList(@RequestParam String systemFrom,@RequestParam String workCode,@RequestParam String dealCode) throws Exception
    {
        List<ConfigAfterDealBo> boList = configAfterDealService.queryConfigAfterDealBo(systemFrom,workCode,dealCode);
        List<ConfigInWarehouseVo> voList = BeanListUtil.copyListProperties(boList,ConfigInWarehouseVo.class);
        return ApiResult.ok(voList);
    }

}

