package ao.adnlogico.nuntius.multitenant.tenant.doctemplate;

import ao.adnlogico.nuntius.multitenant.tenant.department.*;
import ao.adnlogico.nuntius.multitenant.tenant.category.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface DocTemplateRepository extends JpaRepository<DocTemplate, Integer>
{

}
