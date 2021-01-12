package com.wms.util.tools;

import com.wms.api.account.OutWarehouseAccountVo;
import com.wms.model.bo.account.AccountCertificateBo;
import org.springframework.beans.BeanUtils;

/**
 * @author puck
 * @date 2021/1/10 8:49 下午
 */
public class OutStorageAccountTools
{
    public static AccountCertificateBo copyCertificateVo(OutWarehouseAccountVo outWarehouseAccountVo)
    {
        if(null == outWarehouseAccountVo || null == outWarehouseAccountVo.getOutWarehouseBillVo() || null == outWarehouseAccountVo.getOutWarehouseBillSubVo())
        {
            return null;
        }

        AccountCertificateBo accountCertificateBo = new AccountCertificateBo();
        BeanUtils.copyProperties(outWarehouseAccountVo.getOutWarehouseBillSubVo(),accountCertificateBo);

        accountCertificateBo.setAccountCode(outWarehouseAccountVo.getAccountCode());
        accountCertificateBo.setWarehouseCode(outWarehouseAccountVo.getOutWarehouseBillVo().getWarehouseCode());
        accountCertificateBo.setWorkCode(outWarehouseAccountVo.getOutWarehouseBillVo().getWorkCode());
        accountCertificateBo.setDealCode(outWarehouseAccountVo.getOutWarehouseBillVo().getDealCode());

        return accountCertificateBo;
    }
}
