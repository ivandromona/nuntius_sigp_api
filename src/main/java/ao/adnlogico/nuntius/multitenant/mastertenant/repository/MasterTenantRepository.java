package ao.adnlogico.nuntius.multitenant.mastertenant.repository;

import ao.adnlogico.nuntius.multitenant.mastertenant.entity.MasterTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant, Integer>
{

    MasterTenant findByTenantClientId(Integer clientId);
}
