package ao.adnlogico.sigp.nuntius.master;

/**
 * @author Md. Amran Hossain
 */
public interface MasterTenantService
{

    MasterTenant findByClientId(Integer clientId);
}
