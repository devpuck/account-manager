package com.wms.mapper.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.model.entity.ConfigAfterDealEntity;
import com.wms.api.config.ConfigAfterDealQueryParam;
import com.wms.model.bo.config.ConfigAfterDealBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 * 后处理配置 Mapper 接口
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Repository
public interface ConfigAfterDealMapper extends BaseMapper<ConfigAfterDealEntity> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    ConfigAfterDealBo getConfigAfterDealById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param configAfterDealQueryParam
     * @return
     */
    IPage<ConfigAfterDealBo> getConfigAfterDealPageList(@Param("page") Page page, @Param("param") ConfigAfterDealQueryParam configAfterDealQueryParam);

}
