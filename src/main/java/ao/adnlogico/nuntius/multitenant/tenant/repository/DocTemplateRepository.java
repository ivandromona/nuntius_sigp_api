package ao.adnlogico.nuntius.multitenant.tenant.repository;

import ao.adnlogico.nuntius.multitenant.tenant.doc_template.DocTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface DocTemplateRepository extends JpaRepository<DocTemplate, Long>
{

}
