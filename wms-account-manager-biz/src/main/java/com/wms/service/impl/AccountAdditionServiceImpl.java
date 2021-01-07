package com.wms.service.impl;

import com.xac.core.constant.CoreConstant;
import com.wms.model.entity.AccountAdditionEntity;
import com.wms.mapper.account.AccountAdditionMapper;
import com.wms.service.AccountAdditionService;
import com.wms.api.account.AccountAdditionQueryParam;
import com.wms.model.bo.account.AccountAdditionBo;
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
 * 后处理配置 服务实现类
 * </pre>
 *
 * @author puck
 * @since 2020-12-25
 */
@Slf4j
@Service
public class AccountAdditionServiceImpl extends BaseServiceImpl<AccountAdditionMapper, AccountAdditionEntity> implements AccountAdditionService {

    @Autowired
    private AccountAdditionMapper accountAdditionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAccountAddition(AccountAdditionBo accountAddition) {
        AccountAdditionEntity entity = new AccountAdditionEntity();
        BeanUtils.copyProperties(accountAddition , entity);
        return super.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateAccountAddition(AccountAdditionBo accountAddition) {
        AccountAdditionEntity entity = new AccountAdditionEntity();
        BeanUtils.copyProperties(accountAddition , entity);
        return super.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteAccountAddition(Long id) {
        return super.removeById(id);
    }

    @Override
    public AccountAdditionBo getAccountAdditionById(Serializable id) {
        return accountAdditionMapper.getAccountAdditionById(id);
    }

    @Override
    public Paging<AccountAdditionBo> getAccountAdditionPageList(AccountAdditionQueryParam accountAdditionQueryParam) {
        Page page = setPageParam(accountAdditionQueryParam, OrderItem.desc(CoreConstant.CREATED_DATE));
        IPage<AccountAdditionBo> iPage = accountAdditionMapper.getAccountAdditionPageList(page, accountAdditionQueryParam);
        return new Paging(iPage);
    }

}
