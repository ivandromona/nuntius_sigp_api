package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.catalog_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface CataloguingDataRepository extends JpaRepository<CataloguingData, Long>
{

}
