package ao.adnlogico.multitenant.nuntius.tenant.roletype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface RoleTypeRepository extends JpaRepository<RoleType, Integer>
{

}
