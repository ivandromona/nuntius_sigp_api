package ao.adnlogico.nuntius.multitenant.tenant.message;

import ao.adnlogico.nuntius.multitenant.tenant.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>
{

}
