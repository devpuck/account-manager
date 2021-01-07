package com.wms.api.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.xac.core.vo.BaseVo;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <pre>
 * 库房台账表 查询结果对象
 * </pre>
 *
 * @author puck
 * @date 2020-12-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AccountVo对象", description = "库房台账表查询参数")
public class AccountVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表id")
    private Long id;

    @ApiModelProperty(value = "台账编码，自动生成")
    private String accountCode;

    @ApiModelProperty(value = "单据号")
    private String billCode;

    @ApiModelProperty(value = "单据子项编号")
    private String subBillCode;

    @ApiModelProperty(value = "事务编号")
    private String workCode;

    @ApiModelProperty(value = "移动编号，如合同到货、紧急到货。相当于WORK_CODE的一个补充,对接系统的需要，实际操作只需要配置即可，无需理会。主要用于处理老事务处理不规范情况")
    private String dealCode;

    @ApiModelProperty(value = "库房编号")
    private String warehouseCode;

    @ApiModelProperty(value = "库位号")
    private String warehouseLocationCode;

    @ApiModelProperty(value = "产品编码")
    private String productionCode;

    @ApiModelProperty(value = "是否为父件，0父件、1子件、2非父子件关系")
    private String isParent;

    @ApiModelProperty(value = "物料制造处理状态，produce 在制品，clout 余料，waste废料,正常normal")
    private String produceStatus;

    @ApiModelProperty(value = "父产品编码")
    private String parentProductionCode;

    @ApiModelProperty(value = "项目编码，批次属性")
    private String projectCode;

    @ApiModelProperty(value = "合同编号")
    private String contractCode;

    @ApiModelProperty(value = "派工号，FO执行序列号")
    private String dispatchCode;

    @ApiModelProperty(value = "牌号，本为物料属性，但是部分为1：n关系，目前无后期逻辑处理，仅仅记录")
    private String brand;

    @ApiModelProperty(value = "规格，本为物料属性，但是部分为1：n关系，目前无后期逻辑处理，仅仅记录")
    private String specifications;

    @ApiModelProperty(value = "状态/级别/型号，本为物料属性，但是部分为1：n关系，目前无后期逻辑处理，仅仅记录")
    private String productionLevel;

    @ApiModelProperty(value = "技术条件")
    private String technicalConditions;

    @ApiModelProperty(value = "附加技术条件，本为物料属性，但是部分为1：n关系，目前无后期逻辑处理，仅仅记录")
    private String additionalTechnicalConditions;

    @ApiModelProperty(value = "三期代码，批次属性")
    private String threePeriodCode;

    @ApiModelProperty(value = "生产日期，批次属性")
    private Date manufactureDate;

    @ApiModelProperty(value = "过期日期，批次属性")
    private Date expirationDate;

    @ApiModelProperty(value = "批次号，批次属性")
    private String batch;

    @ApiModelProperty(value = "炉批号或小号，批次属性")
    private String splysotCode;

    @ApiModelProperty(value = "机型，批次属性")
    private String aircraftCode;

    @ApiModelProperty(value = "架次，批次属性")
    private String sortieCode;

    @ApiModelProperty(value = "版号，批次属性")
    private String modelCode;

    @ApiModelProperty(value = "构型号，批次属性")
    private String constructionCode;

    @ApiModelProperty(value = "质量编号，批次属性")
    private String qualityCode;

    @ApiModelProperty(value = "质检状态，批次属性")
    private String qualityStatus;

    @ApiModelProperty(value = "状态，批次属性")
    private String status;

    @ApiModelProperty(value = "合格证号，批次属性")
    private String certificateNo;

    @ApiModelProperty(value = "供方合格证号")
    private String supplyCertificateNo;

    @ApiModelProperty(value = "提供者类型")
    private String supplierType;

    @ApiModelProperty(value = "提供者")
    private String supplierBy;

    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "占用数量")
    private BigDecimal occupyQuantity;

    @ApiModelProperty(value = "计价规则，仅仅采购价生效，如果为计划价，则仅仅记录入库时计划价，不做处理")
    private String priceCategory;

    @ApiModelProperty(value = "计价")
    private BigDecimal price;

    @ApiModelProperty(value = "是否寄售")
    private String isConsignment;

    @ApiModelProperty(value = "物料尺寸，本身为物料属性，此处尺寸仅仅在物料状态为余料时有效")
    private String productionSize;

    @ApiModelProperty(value = "货主编码")
    private String productionOwnerCode;

    @ApiModelProperty(value = "货主类型")
    private String productionOwnerType;

    @ApiModelProperty(value = "备注")
    private String details;

private String attribute1;

private String attribute2;

private String attribute3;

private String attribute4;

private String attribute5;

private String attribute6;

private String attribute7;

private String attribute8;

private String attribute9;

private String attribute10;

private String version;

}
