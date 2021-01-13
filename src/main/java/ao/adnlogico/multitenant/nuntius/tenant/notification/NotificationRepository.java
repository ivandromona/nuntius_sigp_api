package ao.adnlogico.multitenant.nuntius.tenant.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>
{

}
