package ao.adnlogico.multitenant.nuntius.tenant.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Domingos M. Fernando
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

    User findByEmail(String userName);
}
