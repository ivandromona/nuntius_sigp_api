package ao.adnlogico.sigp.nuntius.tenant.repository;

import ao.adnlogico.sigp.nuntius.tenant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Md. Amran Hossain
 */
public interface ProductRepository extends JpaRepository<Product, Integer>
{
}
