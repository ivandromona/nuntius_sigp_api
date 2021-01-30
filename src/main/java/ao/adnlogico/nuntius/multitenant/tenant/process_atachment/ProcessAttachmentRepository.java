package ao.adnlogico.nuntius.multitenant.tenant.process_atachment;

import ao.adnlogico.nuntius.multitenant.tenant.process_atachment.ProcessAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ProcessAttachmentRepository extends JpaRepository<ProcessAttachment, Long>
{

}
