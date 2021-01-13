package ao.adnlogico.multitenant.nuntius.tenant.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>
{

}
