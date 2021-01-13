package ao.adnlogico.multitenant.nuntius.tenant.processatachments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ProcessAttachmentRepository extends JpaRepository<ProcessAttachment, Integer>
{

}
