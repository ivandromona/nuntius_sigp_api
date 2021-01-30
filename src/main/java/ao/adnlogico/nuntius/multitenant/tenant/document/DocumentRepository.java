package ao.adnlogico.nuntius.multitenant.tenant.document;

import ao.adnlogico.nuntius.multitenant.tenant.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>
{

}
