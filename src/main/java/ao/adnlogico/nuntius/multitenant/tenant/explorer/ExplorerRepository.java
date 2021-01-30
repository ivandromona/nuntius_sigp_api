package ao.adnlogico.nuntius.multitenant.tenant.explorer;

import ao.adnlogico.nuntius.multitenant.tenant.explorer.Explorer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ExplorerRepository extends JpaRepository<Explorer, Long>
{

}
