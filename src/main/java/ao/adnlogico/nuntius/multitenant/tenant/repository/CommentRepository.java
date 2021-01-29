package ao.adnlogico.nuntius.multitenant.tenant.repository;

import ao.adnlogico.nuntius.multitenant.tenant.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{

}
