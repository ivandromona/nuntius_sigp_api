package ao.adnlogico.nuntius.tenant.repository;

import ao.adnlogico.nuntius.tenant.entity.Entities;
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
