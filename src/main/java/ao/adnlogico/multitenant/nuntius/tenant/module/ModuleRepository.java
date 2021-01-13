package ao.adnlogico.multitenant.nuntius.tenant.module;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer>
{

}
