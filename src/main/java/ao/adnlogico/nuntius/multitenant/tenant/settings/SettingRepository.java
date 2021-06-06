package ao.adnlogico.nuntius.multitenant.tenant.settings;

import ao.adnlogico.nuntius.multitenant.tenant.settings.Setting;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface SettingRepository extends JpaRepository<Setting, Long>
{

    Optional<Setting> findBySettingKey(String settingKey);

}
