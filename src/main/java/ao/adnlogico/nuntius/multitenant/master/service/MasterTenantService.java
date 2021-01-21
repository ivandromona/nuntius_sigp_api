package ao.adnlogico.nuntius.multitenant.master.service;

import ao.adnlogico.nuntius.multitenant.master.entity.MasterTenant;

/**
 * @author Domingos M. Fernando
 */
public interface MasterTenantService
{

    MasterTenant findByClientId(String clientId);
}
