package com.wms.model.bo.config;

import com.xac.core.bo.BaseBo;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;

/**
 * <pre>
 * 后处理配置 查询结果业务对象
 * </pre>
 *
 * @author puck
 * @date 2020-12-22
 */
@Data
@Accessors(chain = true)
public class ConfigAfterDealBo extends BaseBo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
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
