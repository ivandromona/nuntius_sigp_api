package ao.adnlogico.multitenant.nuntius.tenant.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>
{

}
