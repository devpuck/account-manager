package com.wms.service;

import com.wms.model.entity.AccountCertificateEntity;
import com.xac.core.service.BaseService;
import com.wms.api.account.AccountCertificateQueryParam;
import com.wms.model.bo.account.AccountCertificateBo;
import com.xac.core.api.Paging;

import java.io.Serializable;

/**
 * <pre>
 * 库房流水，移动凭证表 服务类
 * </pre>
 *
 * @author puck
 * @since 2021-01-05
 */
public interface AccountCertificateService extends BaseService<AccountCertificateEntity>
{

    /**
     * 保存
     *
     * @param accountCertificate
     * @return
     * @throws Exception
     */
    boolean saveAccountCertificate(AccountCertificateBo accountCertificate);

    /**
     * 修改
     *
     * @param accountCertificate
     * @return
     * @throws Exception
     */
    boolean updateAccountCertificate(AccountCertificateBo accountCertificate);

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteAccountCertificate(Long id);

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    AccountCertificateBo getAccountCertificateById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param accountCertificateQueryParam
     * @return
     * @throws Exception
     */
    Paging<AccountCertificateBo> getAccountCertificatePageList(AccountCertificateQueryParam accountCertificateQueryParam);

    /**
     * 反冲操作
     * @param accountCertificateBo
     * @return
     */
    public int rebackAccount(AccountCertificateBo accountCertificateBo);

}
