package com.wms.model.bo.account;

import com.xac.core.bo.BaseBo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <pre>
 * 库房流水，移动凭证表 查询结果业务对象
 * </pre>
 *
 * @author puck
 * @date 2021-01-05
 */
@Data
@Accessors(chain = true)
public class AccountCertificateBo extends BaseBo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    private Long id;

    /**
     * 台账编码，自动生成
     */
    private String accountCode;

    /**
     * 单据编号
     */
    private String billCode;

    /**
     * 单据子项编号
     */
    private String subBillCode;

    /**
     * 事务编号
     */
    private String workCode;

    /**
     * 移动编号，如合同到货、紧急到货。相当于WORK_CODE的一个补充,对接系统的需要，实际操作只需要配置即可，无需理会。主要用于处理老事务处理不规范情况
     */
    private String dealCode;

    /**
     * 移动数量
     */
    private BigDecimal moveQuantity;

    /**
     * 移动前数量
     */
    private BigDecimal beforeQuantity;

    /**
     * 移动后数量
     */
    private BigDecimal endQuantity;

    /**
     * 移动前组织
     */
    private String fromOrganization;

    /**
     * 移动后组织
     */
    private String toOrganization;

    /**
     * 计价规则
     */
    private String priceCategory;

    /**
     * 移动价格
     */
    private BigDecimal movePrice;

    /**
     * 计价单位
     */
    private String priceUnit;

    /**
     * 入库时价格
     */
    private BigDecimal beforePrice;

    /**
     * 出库时价格
     */
    private BigDecimal endPrice;

    /**
     * 移动前状态
     */
    private String beforeStatus;

    /**
     * 移动后状态
     */
    private String endStatus;

    /**
     * 移动前质量状态
     */
    private String beforeQualityStatus;

    /**
     * 移动后质量状态
     */
    private String endQualityStatus;

    /**
     * 移动人
     */
    private String moveOperator;

    /**
     * 移动部门
     */
    private String moveDept;

    /**
     * 库房编号
     */
    private String warehouseCode;

    /**
     * 库位编号
     */
    private String warehouseLocationCode;

    /**
     * 物料编号
     */
    private String productionCode;

    /**
     * 是否为父件，0父件、1子件、2非父子件关系
     */
    private String isParent;

    /**
     * 物料制造处理状态，produce 在制品，clout 余料，waste废料
     */
    private String produceStatus;

    /**
     * 父产品编码
     */
    private String parentProductionCode;

    /**
     * 项目编码，批次属性
     */
    private String projectCode;

    /**
     * 合同号
     */
    private String contractCode;

    /**
     * 构型号
     */
    private String dispatchCode;

    /**
     * 牌号，本为物料属性，但是部分为1：n关系，目前无后期逻辑处理，仅仅记录
     */
    private String brand;

    /**
     * 规格，本为物料属性，但是部分为1：n关系，目前无后期逻辑处理，仅仅记录
     */
    private String specifications;

    /**
     * 状态/级别/型号，本为物料属性，但是部分为1：n关系，目前无后期逻辑处理，仅仅记录
     */
    private String productionLevel;

    /**
     * 技术条件
     */
    private String technicalConditions;

    /**
     * 附加技术条件，本为物料属性，但是部分为1：n关系，目前无后期逻辑处理，仅仅记录
     */
    private String additionalTechnicalConditions;

    /**
     * 三期代码，批次属性
     */
    private String threePeriodCode;

    /**
     * 生产日期，批次属性
     */
    private Date manufactureDate;

    /**
     * 过期日期，批次属性
     */
    private Date expirationDate;

    /**
     * 批次号，批次属性
     */
    private String batch;

    /**
     * 炉批号或小号，批次属性
     */
    private String splysotCode;

    /**
     * 机型，批次属性
     */
    private String aircraftCode;

    /**
     * 架次，批次属性
     */
    private String sortieCode;

    /**
     * 版号，批次属性
     */
    private String modelCode;

    /**
     * 构型号，批次属性
     */
    private String constructionCode;

    /**
     * 质量编号，批次属性
     */
    private String qualityCode;

    /**
     * 合格证号，批次属性
     */
    private String certificateNo;

    /**
     * 供方合格证号
     */
    private String supplyCertificateNo;

    /**
     * 提供者类型
     */
    private String supplierType;

    /**
     * 提供者
     */
    private String supplierBy;

    /**
     * 是否寄售
     */
    private String isConsignment;

    /**
     * 物料尺寸，本身为物料属性，此处尺寸仅仅在物料状态为余料时有效
     */
    private String productionSize;

    /**
     * 货主编码
     */
    private String productionOwnerCode;

    /**
     * 货主类型
     */
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

    /**
     * 条码
     */
    private String barCode;

    /**
     * 封存日期
     */
    private String storageDate;

    /**
     * 成品序列号
     */
    private String serialNumber;

    /**
     * 成品序列号
     */
    private String manufacturer;

    /**
     * 产品信息
     */
    private String productionInfo;

    /**
     * 物品码
     */
    private String productionCodeInfo;

    private String version;

    /**
     * 移动时间
     */
    private Date moveTime;

    /**
     * 状态
     */
    private String status;

}
