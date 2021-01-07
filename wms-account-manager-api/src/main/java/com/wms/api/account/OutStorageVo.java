package com.wms.api.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author puck
 * @date 2021/1/5 3:03 下午
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "出库过账", description = "出库过账")
public class OutStorageVo
{

    @ApiModelProperty(value = "出库的数量")
    private BigDecimal increaseQuantity;
}
