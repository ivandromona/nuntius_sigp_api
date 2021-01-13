package ao.adnlogico.multitenant.nuntius.tenant.forwarding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ForwardingRepository extends JpaRepository<Forwarding, Integer>
{

}
