package ao.adnlogico.sigp.nuntius.master.repository;

import ao.adnlogico.sigp.nuntius.master.entity.MasterTenant;
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
