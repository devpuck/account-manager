package com.wms.api.account;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.xac.core.api.SortQueryParam;

/**
 * <pre>
 * 后处理配置 查询参数对象
 * </pre>
 *
 * @author puck
 * @date 2020-12-25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AccountAdditionQueryParam对象", description = "后处理配置查询参数")
public class AccountAdditionQueryParam extends SortQueryParam {
    private static final long serialVersionUID = 1L;
}
