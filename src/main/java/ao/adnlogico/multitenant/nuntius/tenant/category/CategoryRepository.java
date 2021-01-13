package ao.adnlogico.multitenant.nuntius.tenant.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>
{

}
