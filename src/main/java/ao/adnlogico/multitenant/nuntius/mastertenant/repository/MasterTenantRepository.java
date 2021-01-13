package ao.adnlogico.multitenant.nuntius.mastertenant.repository;

import ao.adnlogico.multitenant.nuntius.mastertenant.entity.MasterTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Domingos M. Fernando
 */
@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant, Integer>
{

    MasterTenant findByClientId(String clientId);
}
