package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface CataloguingDictionaryRepository extends JpaRepository<CataloguingDictionary, Long>
{

}
