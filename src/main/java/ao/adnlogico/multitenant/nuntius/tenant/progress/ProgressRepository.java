package ao.adnlogico.multitenant.nuntius.tenant.progress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ProgressRepository extends JpaRepository<Progress, Integer>
{

}
