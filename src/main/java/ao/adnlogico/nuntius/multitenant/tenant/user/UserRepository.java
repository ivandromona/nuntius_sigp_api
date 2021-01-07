package ao.adnlogico.nuntius.multitenant.tenant.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer>
{

    Users findByUserName(String userName);
}
