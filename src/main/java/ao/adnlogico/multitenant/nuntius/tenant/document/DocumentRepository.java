package ao.adnlogico.multitenant.nuntius.tenant.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>
{

}
