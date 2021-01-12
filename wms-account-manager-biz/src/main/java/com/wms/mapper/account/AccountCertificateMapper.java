package com.wms.mapper.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.model.entity.AccountCertificateEntity;
import com.wms.api.account.AccountCertificateQueryParam;
import com.wms.model.bo.account.AccountCertificateBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 * 库房流水，移动凭证表 Mapper 接口
 * </pre>
 *
 * @author puck
 * @since 2021-01-05
 */
@Repository
public interface AccountCertificateMapper extends BaseMapper<AccountCertificateEntity> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    AccountCertificateBo getAccountCertificateById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param accountCertificateQueryParam
     * @return
     */
    IPage<AccountCertificateBo> getAccountCertificatePageList(@Param("page") Page page, @Param("param") AccountCertificateQueryParam accountCertificateQueryParam);

    /**
     *
     * @param id
     * @param status
     * @return
     */
    public boolean updateAccountCertificateStatus(Long id,String status);

}
