package com.wms.controller;

import com.xac.core.util.BeanListUtil;
import com.wms.model.entity.AccountCertificateEntity;
import com.wms.service.AccountCertificateService;
import com.wms.api.account.AccountCertificateQueryParam;
import com.wms.api.account.AccountCertificateVo;
import com.wms.model.bo.account.AccountCertificateBo;
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
 * 库房流水，移动凭证表 前端控制器
 * </pre>
 *
 * @author puck
 * @since 2021-01-05
 */
@Slf4j
@RestController
@RequestMapping("/account")
@Api("库房流水，移动凭证表 API")
public class AccountCertificateController extends BaseController {

    @Autowired
    private AccountCertificateService accountCertificateService;

    /**
     * 添加库房流水，移动凭证表
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加AccountCertificate对象", notes = "添加库房流水，移动凭证表", response = ApiResult.class)
    public ApiResult<Boolean> addAccountCertificate(@Valid @RequestBody AccountCertificateVo accountCertificate) throws Exception {
         AccountCertificateBo bo = new AccountCertificateBo();
        BeanUtils.copyProperties(accountCertificate,bo);

        boolean flag = accountCertificateService.saveAccountCertificate(bo);
        return ApiResult.result(flag);
    }

    /**
     * 修改库房流水，移动凭证表
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改AccountCertificate对象", notes = "修改库房流水，移动凭证表", response = ApiResult.class)
    public ApiResult<Boolean> updateAccountCertificate(@Valid @RequestBody AccountCertificateVo accountCertificate) throws Exception {
        AccountCertificateBo bo = new AccountCertificateBo();
        BeanUtils.copyProperties(accountCertificate,bo);

        boolean flag = accountCertificateService.updateAccountCertificate(bo);
        return ApiResult.result(flag);
    }

    /**
     * 删除库房流水，移动凭证表
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除AccountCertificate对象", notes = "删除库房流水，移动凭证表", response = ApiResult.class)
    public ApiResult<Boolean> deleteAccountCertificate(@PathVariable("id") Long id) throws Exception {
        boolean flag = accountCertificateService.deleteAccountCertificate(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取库房流水，移动凭证表
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取AccountCertificate对象详情", notes = "查看库房流水，移动凭证表", response = AccountCertificateVo.class)
    public ApiResult<AccountCertificateVo> getAccountCertificate(@PathVariable("id") Long id) throws Exception {
        AccountCertificateBo accountCertificateBo = accountCertificateService.getAccountCertificateById(id);
        AccountCertificateVo queryVo = null;
        if (accountCertificateBo != null) {
            queryVo = new AccountCertificateVo();
            BeanUtils.copyProperties(accountCertificateBo , queryVo);
        }
        return ApiResult.ok(queryVo);
    }

    /**
     * 库房流水，移动凭证表分页列表
     */
    @PostMapping("/pagelist")
    @ApiOperation(value = "获取AccountCertificate分页列表", notes = "库房流水，移动凭证表分页列表", response = AccountCertificateVo.class)
    public ApiResult<Paging<AccountCertificateVo>> getAccountCertificatePageList(@Valid @RequestBody AccountCertificateQueryParam accountCertificateQueryParam) throws Exception {
        Paging<AccountCertificateBo> paging = accountCertificateService.getAccountCertificatePageList(accountCertificateQueryParam);
        Paging<AccountCertificateVo> resultPage = new Paging<>();
        resultPage.setTotal(paging.getTotal());
        resultPage.setRecords(BeanListUtil.copyListProperties(paging.getRecords(), AccountCertificateVo.class));
        return ApiResult.ok(resultPage);
    }

}

