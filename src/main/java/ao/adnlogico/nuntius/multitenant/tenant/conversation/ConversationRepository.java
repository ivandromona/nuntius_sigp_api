package ao.adnlogico.nuntius.multitenant.tenant.conversation;

import ao.adnlogico.nuntius.multitenant.tenant.conversation.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long>
{

}
