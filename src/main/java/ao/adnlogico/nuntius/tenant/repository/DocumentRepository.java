package ao.adnlogico.nuntius.tenant.repository;

import ao.adnlogico.nuntius.tenant.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>
{

}
