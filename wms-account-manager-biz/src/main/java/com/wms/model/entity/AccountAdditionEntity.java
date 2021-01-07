package com.wms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.xac.core.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <pre>
 * 后处理配置
 * </pre>
 *
 * @author puck
 * @since 2020-12-25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("WMS_ACCOUNT_ADDITION")
public class AccountAdditionEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 台账编码，自动生成
     */
    private String accountCode;

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

}
