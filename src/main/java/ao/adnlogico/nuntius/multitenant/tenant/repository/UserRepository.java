package ao.adnlogico.nuntius.multitenant.tenant.repository;

import ao.adnlogico.nuntius.multitenant.tenant.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Domingos M. Fernando
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

    User findByEmail(String userName);
//    User findByUserName(String userName);
}
