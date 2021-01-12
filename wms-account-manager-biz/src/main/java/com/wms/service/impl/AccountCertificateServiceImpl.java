package com.wms.service.impl;

import com.xac.core.constant.CoreConstant;
import com.wms.model.entity.AccountCertificateEntity;
import com.wms.mapper.account.AccountCertificateMapper;
import com.wms.service.AccountCertificateService;
import com.wms.api.account.AccountCertificateQueryParam;
import com.wms.model.bo.account.AccountCertificateBo;
import com.xac.core.service.impl.BaseServiceImpl;
import com.xac.core.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;


/**
 * <pre>
 * 库房流水，移动凭证表 服务实现类
 * </pre>
 *
 * @author puck
 * @since 2021-01-05
 */
@Slf4j
@Service
public class AccountCertificateServiceImpl extends BaseServiceImpl<AccountCertificateMapper, AccountCertificateEntity> implements AccountCertificateService
{

    @Autowired
    private AccountCertificateMapper accountCertificateMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAccountCertificate(AccountCertificateBo accountCertificate)
    {
        AccountCertificateEntity entity = new AccountCertificateEntity();
        BeanUtils.copyProperties(accountCertificate, entity);
        return super.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateAccountCertificate(AccountCertificateBo accountCertificate)
    {
        AccountCertificateEntity entity = new AccountCertificateEntity();
        BeanUtils.copyProperties(accountCertificate, entity);
        return super.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteAccountCertificate(Long id)
    {
        return super.removeById(id);
    }

    @Override
    public AccountCertificateBo getAccountCertificateById(Serializable id)
    {
        return accountCertificateMapper.getAccountCertificateById(id);
    }

    @Override
    public Paging<AccountCertificateBo> getAccountCertificatePageList(AccountCertificateQueryParam accountCertificateQueryParam)
    {
        Page page = setPageParam(accountCertificateQueryParam, OrderItem.desc(CoreConstant.CREATED_DATE));
        IPage<AccountCertificateBo> iPage = accountCertificateMapper.getAccountCertificatePageList(page, accountCertificateQueryParam);
        return new Paging(iPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int rebackAccount(AccountCertificateBo accountCertificateBo)
    {
        String workCode = accountCertificateBo.getWorkCode();
        return 0;
    }

}
