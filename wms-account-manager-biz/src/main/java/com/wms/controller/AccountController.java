package com.wms.controller;

import com.wms.api.account.AccountResult;
import com.wms.api.account.InWarehouseAccountVo;
import com.xac.core.api.ApiResultCode;
import com.xac.core.util.BeanListUtil;
import com.wms.service.AccountService;
import com.wms.api.account.AccountQueryParam;
import com.wms.api.account.AccountVo;
import com.wms.model.bo.account.AccountBo;
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

import java.math.BigDecimal;

/**
 * <pre>
 * 库房台账表 前端控制器
 * </pre>
 *
 * @author puck
 * @since 2020-12-25
 */
@Slf4j
@RestController
@RequestMapping("/account/in")
@Api("库房台账表 API")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    /**
     * 添加库房台账表
     */
/*    @PostMapping("/add")
    @ApiOperation(value = "添加Account对象", notes = "添加库房台账表", response = ApiResult.class)
    public ApiResult<Boolean> addAccount(@Valid @RequestBody AccountVo account) throws Exception {
        AccountBo bo = new AccountBo();
        BeanUtils.copyProperties(account,bo);

        boolean flag = accountService.saveAccount(bo);
        return ApiResult.result(flag);
    }*/

    @PostMapping("/create")
    @ApiOperation(value = "添加Account对象", notes = "添加库房台账表", response = ApiResult.class)
    public ApiResult<AccountResult> createInStorageAccount(@Valid @RequestBody InWarehouseAccountVo inWarehouseAccountVo) throws Exception
    {
//        AccountBo bo = new AccountBo();
//        BeanUtils.copyProperties(account,bo);
        AccountResult accountResult = accountService.createInStorageAccount(inWarehouseAccountVo);
        ApiResult apiResult = new ApiResult();
        if(accountResult.isSuccess())
        {
            apiResult.setSuccess(true);
            apiResult.setCode(ApiResultCode.SUCCESS.getCode());
            apiResult.setMsg(ApiResultCode.SUCCESS.getMsg());
            apiResult.setData(accountResult);
        }else
        {
            apiResult.setSuccess(false);
            apiResult.setCode(ApiResultCode.FAIL.getCode());
            apiResult.setMsg(ApiResultCode.FAIL.getMsg());
            apiResult.setData(accountResult);
        }
        return apiResult;
    }

    /**
     * 修改库房台账表
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改Account对象", notes = "修改库房台账表", response = ApiResult.class)
    public ApiResult<Boolean> updateAccount(@Valid @RequestBody AccountVo account) throws Exception {
        AccountBo bo = new AccountBo();
        BeanUtils.copyProperties(account,bo);

        boolean flag = accountService.updateAccount(bo);
        return ApiResult.result(flag);
    }

    /**
     * 删除库房台账表
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除Account对象", notes = "删除库房台账表", response = ApiResult.class)
    public ApiResult<Boolean> deleteAccount(@PathVariable("id") Long id) throws Exception {
        boolean flag = accountService.deleteAccount(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取库房台账表
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取Account对象详情", notes = "查看库房台账表", response = AccountVo.class)
    public ApiResult<AccountVo> getAccount(@PathVariable("id") Long id) throws Exception {
        AccountBo accountBo = accountService.getAccountById(id);
        AccountVo queryVo = null;
        if (accountBo != null) {
            queryVo = new AccountVo();
            BeanUtils.copyProperties(accountBo , queryVo);
        }
        return ApiResult.ok(queryVo);
    }

    /**
     * 库房台账表分页列表
     */
    @PostMapping("/pagelist")
    @ApiOperation(value = "获取Account分页列表", notes = "库房台账表分页列表", response = AccountVo.class)
    public ApiResult<Paging<AccountVo>> getAccountPageList(@Valid @RequestBody AccountQueryParam accountQueryParam) throws Exception
    {
        Paging<AccountBo> paging = accountService.getAccountPageList(accountQueryParam);
        Paging<AccountVo> resultPage = new Paging<>();
        resultPage.setTotal(paging.getTotal());
        resultPage.setRecords(BeanListUtil.copyListProperties(paging.getRecords(), AccountVo.class));
        return ApiResult.ok(resultPage);
    }

    /**
     * 添加库房台账表
     */
    @PostMapping("/mergeByCode")
    @ApiOperation(value = "添加Account对象", notes = "添加库房台账表", response = ApiResult.class)
    public ApiResult<Boolean> mergeByCode(@RequestParam String accountCode, @RequestParam BigDecimal quantity, @RequestParam String requestCode, @RequestParam String inStorageBillSubID) throws Exception
    {
        boolean flag = accountService.mergeByCode(accountCode,quantity);
        return ApiResult.result(flag);
    }

    /**
     * 添加库房台账表
     */
    @PostMapping("/mergeByID")
    @ApiOperation(value = "添加Account对象", notes = "添加库房台账表", response = ApiResult.class)
    public ApiResult<Boolean> mergeByID(@RequestParam String accountID,@RequestParam BigDecimal quantity,@RequestParam String requestCode, @RequestParam String inStorageBillSubID) throws Exception
    {

        boolean flag = accountService.mergeByID(accountID,quantity);
        return ApiResult.result(flag);
    }
}

