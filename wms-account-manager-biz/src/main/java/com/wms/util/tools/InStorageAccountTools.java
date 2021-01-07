package com.wms.util.tools;

import com.wms.api.account.InWarehouseAccountVo;
import com.wms.api.instorage.InWarehouseBillSubVo;
import com.wms.api.instorage.InWarehouseBillVo;
import com.wms.model.bo.account.AccountBo;
import com.wms.model.bo.account.AccountCertificateBo;
import org.springframework.beans.BeanUtils;

/**
 * @author puck
 * @date 2021/1/5 11:15 上午
 */
public class InStorageAccountTools
{
    public static AccountBo copyAccount(InWarehouseAccountVo inWarehouseAccountVo)
    {
        if(null == inWarehouseAccountVo || null == inWarehouseAccountVo.getInWarehouseBillSubVo())
        {
            return null;
        }
        InWarehouseBillVo inWarehouseBillVo = inWarehouseAccountVo.getInWarehouseBillVo();
        InWarehouseBillSubVo inWarehouseBillSubVo = inWarehouseAccountVo.getInWarehouseBillSubVo();

        AccountBo accountBo = new AccountBo();
        BeanUtils.copyProperties(inWarehouseAccountVo.getInWarehouseBillSubVo(),accountBo);

        accountBo.setQuantity(accountBo.getQuantity());
        accountBo.setWarehouseCode(inWarehouseBillVo.getWarehouseCode());

        return accountBo;
    }

    public static AccountCertificateBo copyCertificateVo(InWarehouseAccountVo inWarehouseAccountVo)
    {
        if(null == inWarehouseAccountVo || null == inWarehouseAccountVo.getInWarehouseBillSubVo())
        {
            return null;
        }

        InWarehouseBillVo inWarehouseBillVo = inWarehouseAccountVo.getInWarehouseBillVo();
        InWarehouseBillSubVo inWarehouseBillSubVo = inWarehouseAccountVo.getInWarehouseBillSubVo();

        AccountCertificateBo accountCertificateBo = new AccountCertificateBo();
        BeanUtils.copyProperties(inWarehouseBillSubVo,accountCertificateBo);

        accountCertificateBo.setWorkCode(inWarehouseBillVo.getWorkCode());
        accountCertificateBo.setDealCode(inWarehouseBillVo.getDealCode());
        accountCertificateBo.setWarehouseCode(inWarehouseBillVo.getWarehouseCode());

//        accountCertificateBo.setWarehouseLocationCode(inWarehouseBillSubVo.getWarehouseLocationCode());
//        accountCertificateBo.setAircraftCode(inWarehouseBillSubVo.getAircraftCode());
//        accountCertificateBo.setProductionCode(inWarehouseBillSubVo.getProductionCode());
        return accountCertificateBo;
    }

}
