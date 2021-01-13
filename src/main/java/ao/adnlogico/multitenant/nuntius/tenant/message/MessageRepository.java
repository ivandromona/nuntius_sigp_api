package ao.adnlogico.multitenant.nuntius.tenant.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>
{

}
