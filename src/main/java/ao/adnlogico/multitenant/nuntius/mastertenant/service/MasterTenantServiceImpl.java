package ao.adnlogico.multitenant.nuntius.mastertenant.service;

import ao.adnlogico.multitenant.nuntius.mastertenant.entity.MasterTenant;
import ao.adnlogico.multitenant.nuntius.mastertenant.repository.MasterTenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Domingos M. Fernando
 */
@Service
public class MasterTenantServiceImpl implements MasterTenantService
{

    private static final Logger LOG = LoggerFactory.getLogger(MasterTenantServiceImpl.class);

    @Autowired
    MasterTenantRepository masterTenantRepository;

    @Override
    public MasterTenant findByClientId(String clientId)
    {
        LOG.info("findByClientId() method call...");
        return masterTenantRepository.findByClientId(clientId);
    }
}
