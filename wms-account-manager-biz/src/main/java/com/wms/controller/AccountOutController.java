package com.wms.controller;

import com.wms.api.account.AccountResult;
import com.wms.api.account.InWarehouseAccountVo;
import com.wms.api.account.OutWarehouseAccountVo;
import com.wms.service.AccountService;
import com.xac.core.api.ApiResult;
import com.xac.core.api.ApiResultCode;
import com.xac.core.api.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author puck
 * @date 2021/1/10 8:29 下午
 */
@Slf4j
@RestController
@RequestMapping("/account/out")
@Api("出库过账API")
public class AccountOutController extends BaseController
{

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    @ApiOperation(value = "出库过账", notes = "出库过账", response = ApiResult.class)
    public ApiResult<AccountResult> createInStorageAccount(@Valid @RequestBody OutWarehouseAccountVo outWarehouseAccountVo) throws Exception
    {
//        AccountBo bo = new AccountBo();
//        BeanUtils.copyProperties(account,bo);
        AccountResult accountResult = accountService.createOutStorageAccount(outWarehouseAccountVo);
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



}
