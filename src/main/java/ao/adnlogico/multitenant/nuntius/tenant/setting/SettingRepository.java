package ao.adnlogico.multitenant.nuntius.tenant.setting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer>
{

}
