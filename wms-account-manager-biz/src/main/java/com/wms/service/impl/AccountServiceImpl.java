package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.api.account.*;
import com.wms.api.batch.BatchAttribute;
import com.wms.db.query.ExcludeNullQueryWrapper;
import com.wms.model.bo.account.AccountCertificateBo;
import com.wms.service.AccountCertificateService;
import com.wms.util.tools.InStorageAccountTools;
import com.wms.util.tools.OutStorageAccountTools;
import com.xac.core.constant.CoreConstant;
import com.wms.model.entity.AccountEntity;
import com.wms.mapper.account.AccountMapper;
import com.wms.service.AccountService;
import com.wms.model.bo.account.AccountBo;
import com.xac.core.service.impl.BaseServiceImpl;
import com.xac.core.api.Paging;
import com.xac.core.util.BeanListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * <pre>
 * 库房台账表 服务实现类
 * </pre>
 *
 * @author puck
 * @since 2020-12-25
 */
@Slf4j
@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountMapper, AccountEntity> implements AccountService
{

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountCertificateService accountCertificateService;
    private String accountCode = null;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAccount(AccountBo account)
    {
        AccountEntity entity = new AccountEntity();
        BeanUtils.copyProperties(account , entity);
        return super.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AccountResult createInStorageAccount(InWarehouseAccountVo inWarehouseAccountVo)
    {
        AccountResult accountResult = new AccountResult();

        boolean result = false;
        if(null == inWarehouseAccountVo.getInWarehouseBillSubVo()||null == inWarehouseAccountVo.getInWarehouseBillSubVo().getId())
        {
            accountResult.setSuccess(false);
            return accountResult;
        }

        AccountBo accountBo = InStorageAccountTools.copyAccount(inWarehouseAccountVo);
        AccountCertificateBo accountCertificateBo = InStorageAccountTools.copyCertificateVo(inWarehouseAccountVo);

        //判断是否需要合并台账，里面函数最好使用继承实现，目前框架限制，后续修改
        AccountBo sameAccountBo = querySameAccount(accountBo);
        if(null == sameAccountBo)
        {
            String accountCode = UUID.randomUUID().toString().replaceAll("-", "");
            accountBo.setAccountCode(accountCode);

            //创建新的台账，创建凭证
            result = createInAccountCertificate(accountCertificateBo,accountBo);
            if(!result)
            {
                accountResult.setSuccess(false);
                return accountResult;
            }

            AccountEntity entity = new AccountEntity();
            BeanUtils.copyProperties(accountBo , entity);

            result = super.save(entity);
        }else
        {

            //合并台账，更新凭证，包含只更新状态。凭证中会带有之前台账信息
            result= createInAccountCertificate(accountCertificateBo,accountBo,sameAccountBo);
            if(!result)
            {
                accountResult.setSuccess(false);
                return accountResult;
            }

            result = mergeByCode(sameAccountBo.getAccountCode(),accountBo.getQuantity());
        }

        accountResult.setSuccess(result);
        if(result)
        {
            AccountVo accountVo = new AccountVo();
            BeanUtils.copyProperties(accountBo,accountVo);

            AccountCertificateVo accountCertificateVo = new AccountCertificateVo();
            BeanUtils.copyProperties(accountCertificateBo,accountCertificateVo);

            List<AccountVo> accountVoList = new ArrayList<AccountVo>();
            accountVoList.add(accountVo);
            List<AccountCertificateVo> accountCertificateVoList = new ArrayList<AccountCertificateVo>();
            accountCertificateVoList.add(accountCertificateVo);

            accountResult.setAccountVoList(accountVoList);
            accountResult.setAccountCertificateVoList(accountCertificateVoList);
        }
        return accountResult;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AccountResult createOutStorageAccount(OutWarehouseAccountVo outWarehouseAccountVo)
    {
        boolean result = false;
        AccountResult accountResult = new AccountResult();
        String accountCode = outWarehouseAccountVo.getAccountCode();
        String billCode = outWarehouseAccountVo.getOutWarehouseBillVo().getBillCode();
        String subBillCode = null;
        if( null != outWarehouseAccountVo.getOutWarehouseBillSubVo().getId())
        {
            subBillCode = outWarehouseAccountVo.getOutWarehouseBillSubVo().getId().toString();
        }

        AccountBo accountBo = queryAccountByCode(accountCode);
        if(null == accountBo)
        {
            accountResult.setSuccess(false);
            return accountResult;
        }

        //判断当前台账是否允许出库
        BigDecimal accountQuantity = accountBo.getQuantity();
        BigDecimal outQuantity = outWarehouseAccountVo.getQuantity();
        BigDecimal tryQuantity = outWarehouseAccountVo.getTryQuantity();
        if(null != tryQuantity)
        {
            outQuantity = outQuantity.add(tryQuantity);
        }

        //库存数量不足，不允许出库
        if(0 < (accountQuantity.compareTo(outQuantity)))
        {
            accountResult.setSuccess(false);
            return accountResult;
        }

        AccountCertificateBo accountCertificateBo = OutStorageAccountTools.copyCertificateVo(outWarehouseAccountVo);
        if(null == accountCertificateBo)
        {
            accountResult.setSuccess(false);
            return accountResult;
        }

        result = createOutAccountCertificate(accountCertificateBo,accountBo,outWarehouseAccountVo);
        if(!result)
        {
            accountResult.setSuccess(false);
            return accountResult;
        }

        result = outStorage(accountCode,outQuantity);
        accountResult.setSuccess(result);
        if(result)
        {
            AccountVo accountVo = new AccountVo();
            BeanUtils.copyProperties(accountBo,accountVo);

            AccountCertificateVo accountCertificateVo = new AccountCertificateVo();
            BeanUtils.copyProperties(accountCertificateBo,accountCertificateVo);

            List<AccountVo> accountVoList = new ArrayList<AccountVo>();
            accountVo.setQuantity(accountVo.getQuantity().add(outQuantity));
            accountVoList.add(accountVo);
            List<AccountCertificateVo> accountCertificateVoList = new ArrayList<AccountCertificateVo>();
            accountCertificateVoList.add(accountCertificateVo);

            accountResult.setAccountVoList(accountVoList);
            accountResult.setAccountCertificateVoList(accountCertificateVoList);

        }

        return accountResult;
    }

    public boolean createInAccountCertificate(AccountCertificateBo accountCertificateBo, AccountBo accountBo)
    {

        accountCertificateBo.setBeforeQuantity(new BigDecimal(0));
        accountCertificateBo.setEndQuantity(accountBo.getQuantity());
        accountCertificateBo.setBeforeQualityStatus(accountBo.getQualityStatus());
        accountCertificateBo.setEndQualityStatus(accountBo.getQualityStatus());
        accountCertificateBo.setBeforeStatus(accountBo.getStatus());
        accountCertificateBo.setEndStatus(accountBo.getStatus());
        accountCertificateBo.setAccountCode(accountBo.getAccountCode());

        return accountCertificateService.saveAccountCertificate(accountCertificateBo);
    }

    public boolean createInAccountCertificate(AccountCertificateBo accountCertificateBo, AccountBo accountBo, AccountBo sameAccountBo)
    {

        accountCertificateBo.setBeforeQuantity(sameAccountBo.getQuantity());
        accountCertificateBo.setEndQuantity(accountBo.getQuantity());
        accountCertificateBo.setBeforeQualityStatus(sameAccountBo.getQualityStatus());
        accountCertificateBo.setEndQualityStatus(accountBo.getQualityStatus());
        accountCertificateBo.setBeforeStatus(sameAccountBo.getStatus());
        accountCertificateBo.setEndStatus(accountBo.getStatus());

        return accountCertificateService.saveAccountCertificate(accountCertificateBo);
    }

    public boolean createOutAccountCertificate(AccountCertificateBo accountCertificateBo,AccountBo accountBo,OutWarehouseAccountVo outWarehouseAccountVo)
    {

        BigDecimal endQuantity = accountBo.getQuantity().subtract((outWarehouseAccountVo.getQuantity().add(outWarehouseAccountVo.getTryQuantity())));
        accountCertificateBo.setBeforeQuantity(accountBo.getQuantity());
        accountCertificateBo.setEndQuantity(endQuantity);

        accountCertificateBo.setBeforeQualityStatus(accountBo.getQualityStatus());
        accountCertificateBo.setEndQualityStatus(accountBo.getQualityStatus());
        accountCertificateBo.setBeforeStatus(accountBo.getStatus());
        accountCertificateBo.setEndStatus(accountBo.getStatus());
        accountCertificateBo.setAccountCode(accountBo.getAccountCode());

        return accountCertificateService.saveAccountCertificate(accountCertificateBo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateAccount(AccountBo account)
    {
        AccountEntity entity = new AccountEntity();
//        BeanUtils.copyProperties(account , entity);
        return super.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteAccount(Long id)
    {
        return super.removeById(id);
    }

    @Override
    public AccountBo getAccountById(Serializable id)
    {
        return accountMapper.getAccountById(id);
    }

    @Override
    public AccountBo queryAccountByCode(String accountCode)
    {
        AccountEntity accountEntity = accountMapper.selectOne(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getAccountCode,accountCode));
        if(null != accountEntity)
        {
            AccountBo accountBo = new AccountBo();
            BeanUtils.copyProperties(accountEntity,accountBo);
            return accountBo;
        }
        return null;
    }

    @Override
    public Paging<AccountBo> getAccountPageList(AccountQueryParam accountQueryParam)
    {
        Page page = setPageParam(accountQueryParam, OrderItem.desc(CoreConstant.CREATED_DATE));
        IPage<AccountBo> iPage = accountMapper.getAccountPageList(page, accountQueryParam);
        return new Paging(iPage);
    }

    @Override
    public AccountBo querySameAccount(AccountBo accountBo)
    {
//         System.out.println("XXXXXXCCCCC:"+accountBo.getCertificateNo());
         AccountEntity accountEntity = accountMapper.selectOne(new ExcludeNullQueryWrapper<AccountEntity>()
                 .eq("WAREHOUSE_CODE",accountBo.getWarehouseCode())
                 .eq("WAREHOUSE_LOCATION_CODE",accountBo.getWarehouseLocationCode())
                 .eq("PRODUCTION_CODE",accountBo.getProductionCode())
                 .eq("AIRCRAFT_CODE",accountBo.getAircraftCode())
                 .eq("BATCH",accountBo.getBatch())
                 .eq("PRODUCE_STATUS",accountBo.getProduceStatus())
                 .eq("PROJECT_CODE",accountBo.getProjectCode())
                 .eq("CONTRACT_CODE",accountBo.getContractCode())
                 .eq("THREE_PERIOD_CODE",accountBo.getThreePeriodCode())
                 .eq("MANUFACTURE_DATE",accountBo.getManufactureDate())
                 .eq("EXPIRATION_DATE",accountBo.getExpirationDate())
                 .eq("SPLYSOT_CODE",accountBo.getSplysotCode())
                 .eq("SORTIE_CODE",accountBo.getSortieCode())
                 .eq("MODEL_CODE",accountBo.getModelCode())
                 .eq("CONSTRUCTION_CODE",accountBo.getConstructionCode())
                 .eq("QUALITY_STATUS",accountBo.getQualityStatus())
                 .eq("STATUS",accountBo.getStatus())
                 .eq("CERTIFICATE_NO",accountBo.getCertificateNo())
                 .eq("SUPPLY_CERTIFICATE_NO",accountBo.getSupplyCertificateNo())
                 .eq("DISPATCH_CODE",accountBo.getDispatchCode())
                 .eq("ATTRIBUTE1",accountBo.getAttribute1())
                 .eq("ATTRIBUTE2",accountBo.getAttribute2())
                 .eq("ATTRIBUTE3",accountBo.getAttribute3())
                 .eq("ATTRIBUTE4",accountBo.getAttribute4())
                 .eq("ATTRIBUTE5",accountBo.getAttribute5())
         );
         if(null != accountEntity)
         {
             AccountBo sameAccountBo = new AccountBo();
             BeanUtils.copyProperties(accountEntity,sameAccountBo);
             return sameAccountBo;
         }

        return null;
    }

    @Override
    public AccountBo querySameAccountByBatchAttributes(BatchAttribute batchAttribute)
    {
        AccountEntity accountEntity = accountMapper.selectOne(new ExcludeNullQueryWrapper<AccountEntity>()
                .eq("WAREHOUSE_CODE",batchAttribute.getWarehouseCode())
                .eq("WAREHOUSE_LOCATION_CODE",batchAttribute.getWarehouseLocationCode())
                .eq("PRODUCTION_CODE",batchAttribute.getProductionCode())
                .eq("AIRCRAFT_CODE",batchAttribute.getAircraftCode())
                .eq("BATCH",batchAttribute.getBatch())
                .eq("PRODUCE_STATUS",batchAttribute.getProduceStatus())
                .eq("PROJECT_CODE",batchAttribute.getProjectCode())
                .eq("CONTRACT_CODE",batchAttribute.getContractCode())
                .eq("THREE_PERIOD_CODE",batchAttribute.getThreePeriodCode())
                .eq("MANUFACTURE_DATE",batchAttribute.getManufactureDate())
                .eq("EXPIRATION_DATE",batchAttribute.getExpirationDate())
                .eq("SPLYSOT_CODE",batchAttribute.getSplysotCode())
                .eq("SORTIE_CODE",batchAttribute.getSortieCode())
                .eq("MODEL_CODE",batchAttribute.getModelCode())
                .eq("CONSTRUCTION_CODE",batchAttribute.getConstructionCode())
                .eq("QUALITY_STATUS",batchAttribute.getQualityStatus())
                .eq("STATUS",batchAttribute.getStatus())
                .eq("CERTIFICATE_NO",batchAttribute.getCertificateNo())
                .eq("SUPPLY_CERTIFICATE_NO",batchAttribute.getSupplyCertificateNo())
                .eq("DISPATCH_CODE",batchAttribute.getDispatchCode())
                .eq("ATTRIBUTE1",batchAttribute.getAttribute1())
                .eq("ATTRIBUTE2",batchAttribute.getAttribute2())
                .eq("ATTRIBUTE3",batchAttribute.getAttribute3())
                .eq("ATTRIBUTE4",batchAttribute.getAttribute4())
                .eq("ATTRIBUTE5",batchAttribute.getAttribute5())
        );
        if(null != accountEntity)
        {
            AccountBo sameAccountBo = new AccountBo();
            BeanUtils.copyProperties(accountEntity,sameAccountBo);
            return sameAccountBo;
        }

        return null;
    }

    @Override
    public List<AccountBo> queryAccountByWarehouse(String warehouseCode)  throws Exception
    {
        List<AccountEntity> accountEntityList = accountMapper.selectList(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getWarehouseCode,warehouseCode)
                .gt(AccountEntity::getQuantity,0));
        if(null == accountEntityList)
        {
            accountEntityList = new ArrayList<AccountEntity>();
        }

        return BeanListUtil.copyListProperties(accountEntityList,AccountBo.class);
    }

    @Override
    public List<AccountBo> queryAccountByWarehouse(String warehouseCode, String state) throws Exception
    {
        List<AccountEntity> accountEntityList = accountMapper.selectList(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getWarehouseCode,warehouseCode)
                .eq(AccountEntity::getStatus,state)
                .gt(AccountEntity::getQuantity,0));
        if(null == accountEntityList)
        {
            accountEntityList = new ArrayList<AccountEntity>();
        }

        return BeanListUtil.copyListProperties(accountEntityList,AccountBo.class);
    }

    @Override
    public List<AccountBo> queryAccountByLocation(String warehouseCode, String locationCode) throws Exception
    {
        List<AccountEntity> accountEntityList = accountMapper.selectList(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getWarehouseCode,warehouseCode)
                .eq(AccountEntity::getWarehouseLocationCode,locationCode)
                .gt(AccountEntity::getQuantity,0));
        if(null == accountEntityList)
        {
            accountEntityList = new ArrayList<AccountEntity>();
        }

        return BeanListUtil.copyListProperties(accountEntityList,AccountBo.class);
    }

    @Override
    public List<AccountBo> queryAccountByLocation(String warehouseCode, String locationCode, String state) throws Exception
    {
        List<AccountEntity> accountEntityList = accountMapper.selectList(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getWarehouseCode,warehouseCode)
                .eq(AccountEntity::getWarehouseLocationCode,locationCode)
                .eq(AccountEntity::getStatus,state)
                .gt(AccountEntity::getQuantity,0));
        if(null == accountEntityList)
        {
            accountEntityList = new ArrayList<AccountEntity>();
        }

        return BeanListUtil.copyListProperties(accountEntityList,AccountBo.class);
    }

    @Override
    public List<AccountBo> queryAccountByProductionCode(String productionCode) throws Exception
    {
        List<AccountEntity> accountEntityList = accountMapper.selectList(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getProjectCode,productionCode)
                .gt(AccountEntity::getQuantity,0));
        if(null == accountEntityList)
        {
            accountEntityList = new ArrayList<AccountEntity>();
        }

        return BeanListUtil.copyListProperties(accountEntityList,AccountBo.class);
    }

    @Override
    public List<AccountBo> queryAccountByProductionCode(String productionCode, String state) throws Exception
    {
        List<AccountEntity> accountEntityList = accountMapper.selectList(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getProjectCode,productionCode)
                .eq(AccountEntity::getStatus,state)
                .gt(AccountEntity::getQuantity,0));
        if(null == accountEntityList)
        {
            accountEntityList = new ArrayList<AccountEntity>();
        }

        return BeanListUtil.copyListProperties(accountEntityList,AccountBo.class);
    }

    @Override
    public List<AccountBo> queryAccountByWarehouseAndProduction(String warehouseCode, String productionCode) throws Exception
    {
        List<AccountEntity> accountEntityList = accountMapper.selectList(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getWarehouseCode,warehouseCode)
                .eq(AccountEntity::getProjectCode,productionCode)
                .gt(AccountEntity::getQuantity,0));
        if(null == accountEntityList)
        {
            accountEntityList = new ArrayList<AccountEntity>();
        }

        return BeanListUtil.copyListProperties(accountEntityList,AccountBo.class);
    }

    @Override
    public List<AccountBo> queryAccountByWarehouseAndProduction(String warehouseCode, String productionCode, String state) throws Exception
    {
        List<AccountEntity> accountEntityList = accountMapper.selectList(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getWarehouseCode,warehouseCode)
                .eq(AccountEntity::getProjectCode,productionCode)
                .eq(AccountEntity::getStatus,state)
                .gt(AccountEntity::getQuantity,0));
        if(null == accountEntityList)
        {
            accountEntityList = new ArrayList<AccountEntity>();
        }

        return BeanListUtil.copyListProperties(accountEntityList,AccountBo.class);
    }

    @Override
    public boolean mergeByCode(String accountCode, BigDecimal quantity)
    {
        return accountMapper.inMergeByCode(accountCode,quantity);
    }

    public boolean outStorage(String accountCode,BigDecimal quantity)
    {
        return accountMapper.outStorage(accountCode,quantity);
    }

    @Override
    public boolean mergeByID(String accountID, BigDecimal quantity)
    {
        return accountMapper.inMergeByID(accountID,quantity);
    }

    public String getAccountCode()
    {
        return accountCode;
    }
}
