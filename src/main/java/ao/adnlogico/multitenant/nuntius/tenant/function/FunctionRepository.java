package ao.adnlogico.multitenant.nuntius.tenant.function;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface FunctionRepository extends JpaRepository<Function, Integer>
{

}
