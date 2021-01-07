package com.wms.service;

import com.wms.model.entity.AccountAdditionEntity;
import com.xac.core.service.BaseService;
import com.wms.api.account.AccountAdditionQueryParam;
import com.wms.model.bo.account.AccountAdditionBo;
import com.xac.core.api.Paging;

import java.io.Serializable;

/**
 * <pre>
 * 后处理配置 服务类
 * </pre>
 *
 * @author puck
 * @since 2020-12-25
 */
public interface AccountAdditionService extends BaseService<AccountAdditionEntity> {

    /**
     * 保存
     *
     * @param accountAddition
     * @return
     * @throws Exception
     */
    boolean saveAccountAddition(AccountAdditionBo accountAddition);

    /**
     * 修改
     *
     * @param accountAddition
     * @return
     * @throws Exception
     */
    boolean updateAccountAddition(AccountAdditionBo accountAddition);

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteAccountAddition(Long id);

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    AccountAdditionBo getAccountAdditionById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param accountAdditionQueryParam
     * @return
     * @throws Exception
     */
    Paging<AccountAdditionBo> getAccountAdditionPageList(AccountAdditionQueryParam accountAdditionQueryParam);

}
