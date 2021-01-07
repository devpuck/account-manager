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
 * @since 2020-12-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("CONFIG_AFTER_DEAL")
public class ConfigAfterDealEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 配置编号
     */
    private String configCode;

    /**
     * 系统来源
     */
    private String systemFrom;

    /**
     * 事务编号
     */
    private String warehouseWorkCode;

    /**
     * 处理类型编号，如合同到货、紧急到货。相当于WORK_CODE的一个补充,对接系统的需要，实际操作只需要配置即可，无需理会，主要用于处理老事务处理不规范情况
     */
    private String warehouseDealCode;

    /**
     * 配置状态
     */
    private String status;

    /**
     * 回掉接口地址
     */
    private String rebackUrl;

    /**
     * 版本
     */
    private String version;

}
