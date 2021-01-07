package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xac.core.constant.CoreConstant;
import com.wms.model.entity.ConfigAfterDealEntity;
import com.wms.mapper.config.ConfigAfterDealMapper;
import com.wms.service.ConfigAfterDealService;
import com.wms.api.config.ConfigAfterDealQueryParam;
import com.wms.model.bo.config.ConfigAfterDealBo;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * <pre>
 * 后处理配置 服务实现类
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Slf4j
@Service
public class ConfigAfterDealServiceImpl extends BaseServiceImpl<ConfigAfterDealMapper, ConfigAfterDealEntity> implements ConfigAfterDealService
{

    @Autowired
    private ConfigAfterDealMapper configAfterDealMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveConfigAfterDeal(ConfigAfterDealBo configAfterDeal)
    {
        ConfigAfterDealBo beforeConfig = queryBeforeConfigDealBo(configAfterDeal);
        if(null != beforeConfig)
        {
            return true;
        }

        ConfigAfterDealEntity entity = new ConfigAfterDealEntity();
        String configCode = configAfterDeal.getConfigCode();
        if(null == configCode || "".equals(configCode))
        {
            configAfterDeal.setConfigCode(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        BeanUtils.copyProperties(configAfterDeal , entity);
        return super.save(entity);
    }

    ConfigAfterDealBo queryBeforeConfigDealBo(ConfigAfterDealBo configAfterDealBo)
    {
        ConfigAfterDealEntity entity = configAfterDealMapper.selectOne(new QueryWrapper<ConfigAfterDealEntity>().lambda()
                .eq(ConfigAfterDealEntity::getWarehouseWorkCode,configAfterDealBo.getWarehouseWorkCode())
                .eq(ConfigAfterDealEntity::getWarehouseDealCode,configAfterDealBo.getWarehouseDealCode())
                .eq(ConfigAfterDealEntity::getSystemFrom,configAfterDealBo.getSystemFrom())
                .eq(ConfigAfterDealEntity::getRebackUrl,configAfterDealBo.getRebackUrl()));
        if(null != entity)
        {
            ConfigAfterDealBo sameConfigAfterDealBo = new ConfigAfterDealBo();
            BeanUtils.copyProperties(entity,sameConfigAfterDealBo);
            return sameConfigAfterDealBo;
        }
        return null;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateConfigAfterDeal(ConfigAfterDealBo configAfterDeal)
    {
        ConfigAfterDealEntity entity = new ConfigAfterDealEntity();
        BeanUtils.copyProperties(configAfterDeal , entity);
        return super.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteConfigAfterDeal(Long id)
    {
        return super.removeById(id);
    }

    @Override
    public ConfigAfterDealBo getConfigAfterDealById(Serializable id)
    {
        return configAfterDealMapper.getConfigAfterDealById(id);
    }

    @Override
    public Paging<ConfigAfterDealBo> getConfigAfterDealPageList(ConfigAfterDealQueryParam configAfterDealQueryParam)
    {
        Page page = setPageParam(configAfterDealQueryParam, OrderItem.desc(CoreConstant.CREATED_DATE));
        IPage<ConfigAfterDealBo> iPage = configAfterDealMapper.getConfigAfterDealPageList(page, configAfterDealQueryParam);
        return new Paging(iPage);
    }

    @Override
    public List<ConfigAfterDealBo> queryConfigAfterDealBo(String systemFrom, String workCode, String dealCode) throws Exception
    {
        List<ConfigAfterDealEntity> entityList = configAfterDealMapper.selectList(new QueryWrapper<ConfigAfterDealEntity>().lambda()
                .eq(ConfigAfterDealEntity::getSystemFrom,systemFrom)
                .eq(ConfigAfterDealEntity::getWarehouseWorkCode,workCode)
                .eq(ConfigAfterDealEntity::getWarehouseDealCode,dealCode));
        if(null == entityList)
        {
            entityList = new ArrayList<ConfigAfterDealEntity>();
        }

        return BeanListUtil.copyListProperties(entityList,ConfigAfterDealBo.class);
    }

}
