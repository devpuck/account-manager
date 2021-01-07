package com.wms.controller;

import com.xac.core.util.BeanListUtil;
import com.wms.model.entity.AccountAdditionEntity;
import com.wms.service.AccountAdditionService;
import com.wms.api.account.AccountAdditionQueryParam;
import com.wms.api.account.AccountAdditionVo;
import com.wms.model.bo.account.AccountAdditionBo;
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

/**
 * <pre>
 * 后处理配置 前端控制器
 * </pre>
 *
 * @author puck
 * @since 2020-12-25
 */
@Slf4j
@RestController
@RequestMapping("/account/addition")
@Api("后处理配置 API")
public class AccountAdditionController extends BaseController {

    @Autowired
    private AccountAdditionService accountAdditionService;

    /**
     * 添加后处理配置
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加AccountAddition对象", notes = "添加后处理配置", response = ApiResult.class)
    public ApiResult<Boolean> addAccountAddition(@Valid @RequestBody AccountAdditionVo accountAddition) throws Exception {
         AccountAdditionBo bo = new AccountAdditionBo();
        BeanUtils.copyProperties(accountAddition,bo);

        boolean flag = accountAdditionService.saveAccountAddition(bo);
        return ApiResult.result(flag);
    }

    /**
     * 修改后处理配置
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改AccountAddition对象", notes = "修改后处理配置", response = ApiResult.class)
    public ApiResult<Boolean> updateAccountAddition(@Valid @RequestBody AccountAdditionVo accountAddition) throws Exception {
        AccountAdditionBo bo = new AccountAdditionBo();
        BeanUtils.copyProperties(accountAddition,bo);

        boolean flag = accountAdditionService.updateAccountAddition(bo);
        return ApiResult.result(flag);
    }

    /**
     * 删除后处理配置
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除AccountAddition对象", notes = "删除后处理配置", response = ApiResult.class)
    public ApiResult<Boolean> deleteAccountAddition(@PathVariable("id") Long id) throws Exception {
        boolean flag = accountAdditionService.deleteAccountAddition(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取后处理配置
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取AccountAddition对象详情", notes = "查看后处理配置", response = AccountAdditionVo.class)
    public ApiResult<AccountAdditionVo> getAccountAddition(@PathVariable("id") Long id) throws Exception {
        AccountAdditionBo accountAdditionBo = accountAdditionService.getAccountAdditionById(id);
        AccountAdditionVo queryVo = null;
        if (accountAdditionBo != null) {
            queryVo = new AccountAdditionVo();
            BeanUtils.copyProperties(accountAdditionBo , queryVo);
        }
        return ApiResult.ok(queryVo);
    }

    /**
     * 后处理配置分页列表
     */
    @PostMapping("/pagelist")
    @ApiOperation(value = "获取AccountAddition分页列表", notes = "后处理配置分页列表", response = AccountAdditionVo.class)
    public ApiResult<Paging<AccountAdditionVo>> getAccountAdditionPageList(@Valid @RequestBody AccountAdditionQueryParam accountAdditionQueryParam) throws Exception {
        Paging<AccountAdditionBo> paging = accountAdditionService.getAccountAdditionPageList(accountAdditionQueryParam);
        Paging<AccountAdditionVo> resultPage = new Paging<>();
        resultPage.setTotal(paging.getTotal());
        resultPage.setRecords(BeanListUtil.copyListProperties(paging.getRecords(), AccountAdditionVo.class));
        return ApiResult.ok(resultPage);
    }

}

