package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.doc_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface CataloguingDocTypeRepository extends JpaRepository<CataloguingDocType, Long>
{

}
