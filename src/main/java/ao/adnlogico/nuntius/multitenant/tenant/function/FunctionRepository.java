package ao.adnlogico.nuntius.multitenant.tenant.function;

import ao.adnlogico.nuntius.multitenant.tenant.function.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface FunctionRepository extends JpaRepository<Function, Long>
{

}
