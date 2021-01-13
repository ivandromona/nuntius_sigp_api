package ao.adnlogico.multitenant.nuntius.tenant.doctemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface DocTemplateRepository extends JpaRepository<DocTemplate, Integer>
{

}
