package ao.adnlogico.multitenant.nuntius.tenant.conversations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer>
{

}
