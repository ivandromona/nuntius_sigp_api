package ao.adnlogico.sigp.nuntius.master.service;

import ao.adnlogico.sigp.nuntius.master.entity.MasterTenant;

/**
 * @author Md. Amran Hossain
 */
public interface MasterTenantService
{

    MasterTenant findByClientId(Integer clientId);
}
