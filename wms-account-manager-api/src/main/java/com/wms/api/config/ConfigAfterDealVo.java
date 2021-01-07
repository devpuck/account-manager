package com.wms.api.config;

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
 * @date 2020-12-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ConfigAfterDealVo对象", description = "后处理配置查询参数")
public class ConfigAfterDealVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表id")
    private Long id;

    @ApiModelProperty(value = "配置编号")
    private String configCode;

    @ApiModelProperty(value = "系统来源")
    private String systemFrom;

    @ApiModelProperty(value = "事务编号")
    private String warehouseWorkCode;

    @ApiModelProperty(value = "处理类型编号，如合同到货、紧急到货。相当于WORK_CODE的一个补充,对接系统的需要，实际操作只需要配置即可，无需理会，主要用于处理老事务处理不规范情况")
    private String warehouseDealCode;

    @ApiModelProperty(value = "配置状态")
    private String status;

    @ApiModelProperty(value = "回掉接口地址")
    private String rebackUrl;

    @ApiModelProperty(value = "版本")
    private String version;

}
