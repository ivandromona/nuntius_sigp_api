package ao.adnlogico.multitenant.nuntius.tenant.explorer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ExplorerRepository extends JpaRepository<Explorer, Integer>
{

}
