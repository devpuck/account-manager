package com.wms.api.account;

import com.wms.api.instorage.InWarehouseBillSubVo;
import com.wms.api.instorage.InWarehouseBillVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author puck
 * @date 2021/1/5 9:48 上午
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "入库过账对象", description = "入库过账对象")
public class InWarehouseAccountVo
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "入库单")
    private InWarehouseBillVo inWarehouseBillVo;

    @ApiModelProperty(value = "入库单子单")
    private InWarehouseBillSubVo inWarehouseBillSubVo;

    @ApiModelProperty(value = "增加的数量")
    private BigDecimal increaseQuantity;

    @ApiModelProperty(value = "库存状态")
    private String status;

}
