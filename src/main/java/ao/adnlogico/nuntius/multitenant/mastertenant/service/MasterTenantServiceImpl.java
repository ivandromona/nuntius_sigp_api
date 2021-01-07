package ao.adnlogico.nuntius.multitenant.mastertenant.service;

import ao.adnlogico.nuntius.multitenant.mastertenant.entity.MasterTenant;
import ao.adnlogico.nuntius.multitenant.mastertenant.repository.MasterTenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Md. Amran Hossain
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
