package ao.adnlogico.nuntius.multitenant.tenant.user.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, Long>
{

}
