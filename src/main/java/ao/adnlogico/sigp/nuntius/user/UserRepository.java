package ao.adnlogico.sigp.nuntius.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

    User findByUserName(String userName);
}
