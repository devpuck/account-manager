package com.wms.api.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.xac.core.vo.BaseVo;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;

/**
 * <pre>
 * 后处理配置 查询结果对象
 * </pre>
 *
 * @author puck
 * @date 2020-12-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AccountAdditionVo对象", description = "后处理配置查询参数")
public class AccountAdditionVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表id")
    private Long id;

    @ApiModelProperty(value = "台账编码，自动生成")
    private String accountCode;

    @ApiModelProperty(value = "条码")
    private String barCode;

    @ApiModelProperty(value = "封存日期")
    private String storageDate;

    @ApiModelProperty(value = "成品序列号")
    private String serialNumber;

    @ApiModelProperty(value = "成品序列号")
    private String manufacturer;

    @ApiModelProperty(value = "产品信息")
    private String productionInfo;

    @ApiModelProperty(value = "物品码")
    private String productionCodeInfo;

}
