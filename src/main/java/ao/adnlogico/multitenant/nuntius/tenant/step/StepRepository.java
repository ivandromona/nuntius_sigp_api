package ao.adnlogico.multitenant.nuntius.tenant.step;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface StepRepository extends JpaRepository<Step, Integer>
{

}
