package ao.adnlogico.nuntius.multitenant.tenant.role_type;

import ao.adnlogico.nuntius.multitenant.tenant.role_type.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface RoleTypeRepository extends JpaRepository<RoleType, Long>
{

}
