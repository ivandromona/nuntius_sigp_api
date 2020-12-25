/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.sigp.nuntius.client.config;

import ao.adnlogico.sigp.nuntius.master.config.DBContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 *
 * @author domingos.fernando
 */
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver
{

    private static final String DEFAULT_TENANT_ID = "client_tenant_1";

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
