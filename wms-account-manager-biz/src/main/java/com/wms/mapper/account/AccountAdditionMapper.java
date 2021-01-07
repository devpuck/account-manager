package com.wms.mapper.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.model.entity.AccountAdditionEntity;
import com.wms.api.account.AccountAdditionQueryParam;
import com.wms.model.bo.account.AccountAdditionBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 * 后处理配置 Mapper 接口
 * </pre>
 *
 * @author puck
 * @since 2020-12-25
 */
@Repository
public interface AccountAdditionMapper extends BaseMapper<AccountAdditionEntity> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    AccountAdditionBo getAccountAdditionById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param accountAdditionQueryParam
     * @return
     */
    IPage<AccountAdditionBo> getAccountAdditionPageList(@Param("page") Page page, @Param("param") AccountAdditionQueryParam accountAdditionQueryParam);

}
