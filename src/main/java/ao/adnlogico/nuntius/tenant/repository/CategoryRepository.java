package ao.adnlogico.nuntius.tenant.repository;

import ao.adnlogico.nuntius.tenant.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>
{

}
