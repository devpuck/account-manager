package com.wms.service;

import com.wms.model.entity.ConfigAfterDealEntity;
import com.xac.core.service.BaseService;
import com.wms.api.config.ConfigAfterDealQueryParam;
import com.wms.model.bo.config.ConfigAfterDealBo;
import com.xac.core.api.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 后处理配置 服务类
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
public interface ConfigAfterDealService extends BaseService<ConfigAfterDealEntity> {

    /**
     * 保存
     *
     * @param configAfterDeal
     * @return
     * @throws Exception
     */
    boolean saveConfigAfterDeal(ConfigAfterDealBo configAfterDeal);

    /**
     * 修改
     *
     * @param configAfterDeal
     * @return
     * @throws Exception
     */
    boolean updateConfigAfterDeal(ConfigAfterDealBo configAfterDeal);

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteConfigAfterDeal(Long id);

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    ConfigAfterDealBo getConfigAfterDealById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param configAfterDealQueryParam
     * @return
     * @throws Exception
     */
    Paging<ConfigAfterDealBo> getConfigAfterDealPageList(ConfigAfterDealQueryParam configAfterDealQueryParam);

    /**
     * 根据库房，事务流程，处理流程查询处理流程
     * @param systemFrom
     * @param workCode
     * @param dealCode
     * @return
     */
    List<ConfigAfterDealBo> queryConfigAfterDealBo(String systemFrom, String workCode,String dealCode) throws Exception;

}
