package com.wms.controller;

import com.wms.api.account.AccountVo;
import com.wms.api.batch.BatchAttribute;
import com.wms.errorcode.ErrorCode;
import com.wms.model.bo.account.AccountBo;
import com.wms.service.AccountService;
import com.wms.util.CheckParameter;
import com.xac.core.api.ApiResult;
import com.xac.core.api.ApiResultCode;
import com.xac.core.api.BaseController;
import com.xac.core.util.BeanListUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author puck
 * @date 2020/12/26 3:18 下午
 */
@Slf4j
@RestController
@RequestMapping("/account/query")
@Api("库房台账表 API")
public class AccountQueryController extends BaseController
{
    @Autowired
    private AccountService accountService;

    /**
     * 根据库房编号查询台账
     */
    @PostMapping("/queryByWarehouse")
    @ApiOperation(value = "添加Account对象", notes = "添加库房台账表", response = ApiResult.class)
    public ApiResult<List<AccountBo>> addAccount(@RequestParam String warehouseCode, @RequestParam String state) throws Exception
    {
        if(ErrorCode.OK!= CheckParameter.checkParameter(warehouseCode))
        {
            return ApiResult.result(ApiResultCode.PARAMETER_EXCEPTION);
        }

        List<AccountBo> accountBoList = accountService.queryAccountByWarehouse(warehouseCode);
        List<AccountVo> accountVoList = BeanListUtil.copyListProperties(accountBoList,AccountVo.class);

        return ApiResult.ok(accountVoList);
    }

    /**
     * 查询相同批次属性的台账
     */
    @PostMapping("/querySameAccount")
    @ApiOperation(value = "查询相同批次属性的台账", notes = "查询相同批次属性的台账", response = AccountVo.class)
    public ApiResult<AccountVo> querySameAccount(@Valid @RequestBody AccountVo accountVo) throws Exception
    {
        AccountBo changeBo = new AccountBo();
        BeanUtils.copyProperties(accountVo,changeBo);
        AccountBo accountBo = accountService.querySameAccount(changeBo);
        AccountVo queryVo = null;
        if (accountBo != null)
        {
            queryVo = new AccountVo();
            BeanUtils.copyProperties(accountBo , queryVo);
        }
        return ApiResult.ok(queryVo);
    }

    /**
     * 查询相同批次属性的台账
     */
    @PostMapping("/querySameAccountByBatchAttribute")
    @ApiOperation(value = "查询相同批次属性的台账", notes = "查询相同批次属性的台账", response = AccountVo.class)
    public ApiResult<AccountVo> querySameAccountByBatchAttributes(@Valid @RequestBody BatchAttribute batchAttribute) throws Exception
    {
        AccountBo accountBo = accountService.querySameAccountByBatchAttributes(batchAttribute);
        AccountVo queryVo = null;
        if (accountBo != null)
        {
            queryVo = new AccountVo();
            BeanUtils.copyProperties(accountBo , queryVo);
        }
        return ApiResult.ok(queryVo);
    }

}
