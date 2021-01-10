package com.wms.api.account;

import com.wms.api.outstorage.OutWarehouseBillSubVo;
import com.wms.api.outstorage.OutWarehouseBillVo;
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
public class OutWarehouseAccountVo
{

    @ApiModelProperty(value = "出库单")
    private OutWarehouseBillVo outWarehouseBillVo;

    @ApiModelProperty(value = "出库子单")
    private OutWarehouseBillSubVo outWarehouseBillSubVo;

    @ApiModelProperty(value = "台账编号")
    private String accountCode;

    @ApiModelProperty(value = "出库的数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "试料数量")
    private BigDecimal tryQuantity;
}
