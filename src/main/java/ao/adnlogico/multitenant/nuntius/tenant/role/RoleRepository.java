package ao.adnlogico.multitenant.nuntius.tenant.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{

}
