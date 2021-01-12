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
 * 库房流水，移动凭证表 查询结果对象
 * </pre>
 *
 * @author puck
 * @date 2021-01-05
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AccountCertificateVo对象", description = "库房流水，移动凭证表查询参数")
public class AccountCertificateVo extends BaseVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表id")
    private Long id;

    @ApiModelProperty(value = "台账编码，自动生成")
    private String accountCode;

    @ApiModelProperty(value = "单据编号")
    private String billCode;

    @ApiModelProperty(value = "单据子项编号")
    private String subBillCode;

    @ApiModelProperty(value = "事务编号")
    private String workCode;

    @ApiModelProperty(value = "移动编号，如合同到货、紧急到货。相当于WORK_CODE的一个补充,对接系统的需要，实际操作只需要配置即可，无需理会。主要用于处理老事务处理不规范情况")
    private String dealCode;

    @ApiModelProperty(value = "移动数量")
    private BigDecimal moveQuantity;

    @ApiModelProperty(value = "移动前数量")
    private BigDecimal beforeQuantity;

    @ApiModelProperty(value = "移动后数量")
    private BigDecimal endQuantity;

    @ApiModelProperty(value = "移动前组织")
    private String fromOrganization;

    @ApiModelProperty(value = "移动后组织")
    private String toOrganization;

    @ApiModelProperty(value = "计价规则")
    private String priceCategory;

    @ApiModelProperty(value = "移动价格")
    private BigDecimal movePrice;

    @ApiModelProperty(value = "计价单位")
    private String priceUnit;

    @ApiModelProperty(value = "入库时价格")
    private BigDecimal beforePrice;

    @ApiModelProperty(value = "出库时价格")
    private BigDecimal endPrice;

    @ApiModelProperty(value = "移动前状态")
    private String beforeStatus;

    @ApiModelProperty(value = "移动后状态")
    private String endStatus;

    @ApiModelProperty(value = "移动前质量状态")
    private String beforeQualityStatus;

    @ApiModelProperty(value = "移动后质量状态")
    private String endQualityStatus;

    @ApiModelProperty(value = "移动人")
    private String moveOperator;

    @ApiModelProperty(value = "移动部门")
    private String moveDept;

    @ApiModelProperty(value = "库房编号")
    private String warehouseCode;

    @ApiModelProperty(value = "库位编号")
    private String warehouseLocationCode;

    @ApiModelProperty(value = "物料编号")
    private String productionCode;

    @ApiModelProperty(value = "是否为父件，0父件、1子件、2非父子件关系")
    private String isParent;

    @ApiModelProperty(value = "物料制造处理状态，produce 在制品，clout 余料，waste废料")
    private String produceStatus;

    @ApiModelProperty(value = "父产品编码")
    private String parentProductionCode;

    @ApiModelProperty(value = "项目编码，批次属性")
    private String projectCode;

    @ApiModelProperty(value = "合同号")
    private String contractCode;

    @ApiModelProperty(value = "构型号")
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

    @ApiModelProperty(value = "合格证号，批次属性")
    private String certificateNo;

    @ApiModelProperty(value = "供方合格证号")
    private String supplyCertificateNo;

    @ApiModelProperty(value = "提供者类型")
    private String supplierType;

    @ApiModelProperty(value = "提供者")
    private String supplierBy;

    @ApiModelProperty(value = "是否寄售")
    private String isConsignment;

    @ApiModelProperty(value = "物料尺寸，本身为物料属性，此处尺寸仅仅在物料状态为余料时有效")
    private String productionSize;

    @ApiModelProperty(value = "货主编码")
    private String productionOwnerCode;

    @ApiModelProperty(value = "货主类型")
    private String productionOwnerType;

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

    private String version;

    @ApiModelProperty(value = "移动时间")
    private Date moveTime;

    @ApiModelProperty(value = "状态")
    private String status;

}
