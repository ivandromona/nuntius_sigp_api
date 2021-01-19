package ao.adnlogico.nuntius.master.services;

import ao.adnlogico.nuntius.master.entity.MasterTenant;

/**
 * @author Domingos M. Fernando
 */
public interface MasterTenantService
{

    MasterTenant findByClientId(String clientId);
}
