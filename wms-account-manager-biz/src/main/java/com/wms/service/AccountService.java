package com.wms.service;

import com.wms.api.account.AccountResult;
import com.wms.api.account.InWarehouseAccountVo;
import com.wms.api.account.OutWarehouseAccountVo;
import com.wms.api.batch.BatchAttribute;
import com.wms.model.entity.AccountEntity;
import com.xac.core.service.BaseService;
import com.wms.api.account.AccountQueryParam;
import com.wms.model.bo.account.AccountBo;
import com.xac.core.api.Paging;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * 库房台账表 服务类
 * </pre>
 *
 * @author puck
 * @since 2020-12-25
 */
public interface AccountService extends BaseService<AccountEntity>
{

    /**
     * 保存
     *
     * @param account
     * @return
     * @throws Exception
     */
    boolean saveAccount(AccountBo account);

    /**
     * 入库创建台账
     * @param inWarehouseAccountVo
     * @return
     */
    AccountResult createInStorageAccount(InWarehouseAccountVo inWarehouseAccountVo);

    /**
     * 出库过账
     * @param outWarehouseAccountVo
     * @return
     */
    AccountResult createOutStorageAccount(OutWarehouseAccountVo outWarehouseAccountVo);

    /**
     * 修改
     *
     * @param account
     * @return
     * @throws Exception
     */
    boolean updateAccount(AccountBo account);

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteAccount(Long id);

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    AccountBo getAccountById(Serializable id);

    /**
     * 根据台账编号查询台账
     * @param accountCode
     * @return
     */
    AccountBo queryAccountByCode(String accountCode);

    /**
     * 获取分页对象
     *
     * @param accountQueryParam
     * @return
     * @throws Exception
     */
    Paging<AccountBo> getAccountPageList(AccountQueryParam accountQueryParam);

    /**
     * 根据批次属性，判断是否可以合并台账，如果有，返回台账
     * @param accountBo
     * @return
     */
    public AccountBo querySameAccount(AccountBo accountBo);

    /**
     * 根据批次属性，判断是否可以合并台账，如果有，返回台账
     * @param batchAttribute
     * @return
     */
    public AccountBo querySameAccountByBatchAttributes(BatchAttribute batchAttribute);

    /**
     * 根据仓库查询台账
     * @param warehouseCode
     * @return
     */
    public List<AccountBo> queryAccountByWarehouse(String warehouseCode)  throws Exception;

    /**
     * 根据仓库查询台账
     * @param warehouseCode
     * @param state
     * @return
     */
    public List<AccountBo> queryAccountByWarehouse(String warehouseCode,String state)  throws Exception;

    /**
     * 根据库房库位查询台账
     * @param warehouseCode
     * @param locationCode
     * @return
     */
    public List<AccountBo> queryAccountByLocation(String warehouseCode,String locationCode)  throws Exception;
    /**
     * 根据库房库位查询台账
     * @param warehouseCode
     * @param locationCode
     * @param state
     * @return
     */
    public List<AccountBo> queryAccountByLocation(String warehouseCode,String locationCode,String state)  throws Exception;

    /**
     * 根据物料查询台账
     * @param productionCode
     * @return
     */
    public List<AccountBo> queryAccountByProductionCode(String productionCode) throws Exception;
    /**
     * 根据物料查询台账
     * @param productionCode
     * @param state
     * @return
     */
    public List<AccountBo> queryAccountByProductionCode(String productionCode,String state) throws Exception;

    /**
     * 根据库房、物料，查询台账
     * @param warehouseCode
     * @param productionCode
     * @return
     */
    public List<AccountBo> queryAccountByWarehouseAndProduction(String warehouseCode,String productionCode) throws Exception;

    /**
     * 根据库房、物料，查询台账
     * @param warehouseCode
     * @param productionCode
     * @param state
     * @return
     */
    public List<AccountBo> queryAccountByWarehouseAndProduction(String warehouseCode,String productionCode,String state) throws Exception;

    /**
     * 如果根据accountCode合并台账
     * @param accountCode
     * @param quantity
     * @return
     */
    public boolean mergeByCode(String accountCode, BigDecimal quantity);

    /**
     * 入库根据ID合并台账
     * @param accountID
     * @param quantity
     * @return
     */
    public boolean mergeByID(String accountID,BigDecimal quantity);
}
