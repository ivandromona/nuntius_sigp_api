package ao.adnlogico.nuntius.multitenant.tenant.config;

import ao.adnlogico.nuntius.multitenant.master.config.DBContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * @author Domingos M. Fernando
 */
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver
{

    private static final String DEFAULT_TENANT_ID = "nuntius_adn_db";

    @Override
    public String resolveCurrentTenantIdentifier()
    {
        String tenant = DBContextHolder.getCurrentDb();
        return StringUtils.isNotBlank(tenant) ? tenant : DEFAULT_TENANT_ID;
    }

    @Override
    public boolean validateExistingCurrentSessions()
    {
        return true;
    }
}
