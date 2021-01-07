package ao.adnlogico.nuntius.multitenant.mastertenant.service;

import ao.adnlogico.nuntius.multitenant.mastertenant.entity.MasterTenant;

/**
 * @author Md. Amran Hossain
 */
public interface MasterTenantService
{

    MasterTenant findByClientId(String clientId);
}
