package ao.adnlogico.nuntius.tenant.repository;

import ao.adnlogico.nuntius.tenant.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>
{

}
