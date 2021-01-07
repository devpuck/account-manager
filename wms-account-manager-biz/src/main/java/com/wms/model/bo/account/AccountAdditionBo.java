package com.wms.model.bo.account;

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
 * @date 2020-12-25
 */
@Data
@Accessors(chain = true)
public class AccountAdditionBo extends BaseBo implements Serializable {
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
