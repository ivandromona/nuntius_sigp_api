package ao.adnlogico.multitenant.nuntius.master.tenant;

/**
 * @author Domingos M. Fernando
 */
public interface MasterTenantService
{

    MasterTenant findByClientId(String clientId);
}
