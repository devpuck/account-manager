package com.wms.model.bo.account;

import com.xac.core.bo.BaseBo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <pre>
 * 库房台账表 查询结果业务对象
 * </pre>
 *
 * @author puck
 * @date 2020-12-25
 */
@Data
@Accessors(chain = true)
public class AccountBo extends BaseBo implements Serializable {
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
     * 库房编号
     */
    private String warehouseCode;

    /**
     * 库位号
     */
    private String warehouseLocationCode;

    /**
     * 产品编码
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
     * 合同编号
     */
    private String contractCode;

    /**
     * 派工号，FO执行序列号
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
     * 质检状态，批次属性
     */
    private String qualityStatus;

    /**
     * 状态，批次属性
     */
    private String status;

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
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 占用数量
     */
    private BigDecimal occupyQuantity;

    /**
     * 计价规则，仅仅采购价生效，如果为计划价，则仅仅记录入库时计划价，不做处理
     */
    private String priceCategory;

    /**
     * 计价规则
     */
    private BigDecimal price;

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

    /**
     * 备注
     */
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
