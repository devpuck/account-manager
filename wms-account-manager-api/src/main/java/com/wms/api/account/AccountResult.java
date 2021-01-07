package com.wms.api.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author puck
 * @date 2021/1/5 8:29 下午
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "台账返回", description = "台账返回，返回台账和凭证")
public class AccountResult
{
    @ApiModelProperty(value = "是否成功")
    boolean isSuccess;

    @ApiModelProperty(value = "台账列表")
    List<AccountVo> accountVoList;

    @ApiModelProperty(value = "流水列表")
    List<AccountCertificateVo> accountCertificateVoList;

    @ApiModelProperty(value = "需求单ID")
    String requirementID;

    @ApiModelProperty(value = "需求子单ID")
    String requirementSubID;
}
