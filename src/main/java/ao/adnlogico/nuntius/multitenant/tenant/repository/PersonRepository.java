package ao.adnlogico.nuntius.multitenant.tenant.repository;

import ao.adnlogico.nuntius.multitenant.tenant.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>
{

}
