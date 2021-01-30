package ao.adnlogico.nuntius.multitenant.master;

/**
 * @author Domingos M. Fernando
 */
public interface MasterTenantService
{

    MasterTenant findByClientId(String clientId);
}
