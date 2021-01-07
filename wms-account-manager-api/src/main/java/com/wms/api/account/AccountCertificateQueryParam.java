package com.wms.api.account;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.xac.core.api.SortQueryParam;

/**
 * <pre>
 * 库房流水，移动凭证表 查询参数对象
 * </pre>
 *
 * @author puck
 * @date 2021-01-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AccountCertificateQueryParam对象", description = "库房流水，移动凭证表查询参数")
public class AccountCertificateQueryParam extends SortQueryParam {
    private static final long serialVersionUID = 1L;
}
