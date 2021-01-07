package com.wms.api.batch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author puck
 * @date 2020/12/25 5:47 下午
 */
@Data
@Accessors(chain = true)
public class BatchAttribute
{
    @ApiModelProperty(value = "库房编号")
    private String warehouseCode;

    @ApiModelProperty(value = "库位号")
    private String warehouseLocationCode;

    @ApiModelProperty(value = "物料编号")
    private String productionCode;

    @ApiModelProperty(value = "物料制造处理状态，produce 在制品，clout 余料，waste废料,正常normal")
    private String produceStatus;

    @ApiModelProperty(value = "项目编码，批次属性")
    private String projectCode;

    @ApiModelProperty(value = "合同编号")
    private String contractCode;

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

    @ApiModelProperty(value = "派工号，FO执行序列号")
    private String dispatchCode;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    private String attribute4;

    private String attribute5;
}
