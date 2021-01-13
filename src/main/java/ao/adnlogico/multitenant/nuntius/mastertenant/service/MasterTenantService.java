package ao.adnlogico.multitenant.nuntius.mastertenant.service;

import ao.adnlogico.multitenant.nuntius.mastertenant.entity.MasterTenant;

/**
 * @author Domingos M. Fernando
 */
public interface MasterTenantService
{

    MasterTenant findByClientId(String clientId);
}
