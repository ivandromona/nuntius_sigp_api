package ao.adnlogico.nuntius.multitenant.tenant.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author domingos.fernando
 */
@Repository
public interface EntityRepository extends JpaRepository<Entities, Integer>
{

}
