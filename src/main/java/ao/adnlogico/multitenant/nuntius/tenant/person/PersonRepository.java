package ao.adnlogico.multitenant.nuntius.tenant.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>
{

}
