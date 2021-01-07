package ao.adnlogico.nuntius.multitenant.tenant.process;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ProcessRepository extends JpaRepository<Process, Integer>
{

}
