package ao.adnlogico.nuntius.multitenant.tenant.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>
{

}
