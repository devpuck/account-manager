package com.wms.mapper.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.model.entity.AccountEntity;
import com.wms.api.account.AccountQueryParam;
import com.wms.model.bo.account.AccountBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * 库房台账表 Mapper 接口
 * </pre>
 *
 * @author puck
 * @since 2020-12-25
 */
@Repository
public interface AccountMapper extends BaseMapper<AccountEntity> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    AccountBo getAccountById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param accountQueryParam
     * @return
     */
    IPage<AccountBo> getAccountPageList(@Param("page") Page page, @Param("param") AccountQueryParam accountQueryParam);

    List<AccountBo> queryAccountByWarehouse(String warehouseCode);

    /**
     * 如果根据accountCode合并台账
     * @param code
     * @param quantity
     * @return
     */
    boolean inMergeByCode(String code, BigDecimal quantity);

    /**
     * 入库根据ID合并台账
     * @param id
     * @param quantity
     * @return
     */
    boolean inMergeByID(String id,BigDecimal quantity);

}
