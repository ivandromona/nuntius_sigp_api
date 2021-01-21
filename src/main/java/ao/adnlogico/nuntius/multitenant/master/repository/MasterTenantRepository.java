package ao.adnlogico.nuntius.multitenant.master.repository;

import ao.adnlogico.nuntius.multitenant.master.entity.MasterTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Domingos M. Fernando
 */
@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant, Long>
{

    MasterTenant findByClientId(String clientId);
}
