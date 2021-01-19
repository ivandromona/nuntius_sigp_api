package ao.adnlogico.nuntius.master.repository;

import ao.adnlogico.nuntius.master.entity.MasterTenant;
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
