package ao.adnlogico.nuntius.multitenant.tenant.cataloguing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface CataloguingRepository extends JpaRepository<Cataloguing, Long>
{

}
