package ao.adnlogico.multitenant.nuntius.tenant.notificationuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface NotificationUserRepository extends JpaRepository<NotificationUser, Integer>
{

}
