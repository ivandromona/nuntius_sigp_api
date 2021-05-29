package ao.adnlogico.nuntius.multitenant.tenant.process_atachment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ProcessAttachmentRepository extends JpaRepository<ProcessAttachment, Long>
{

    @Query("SELECT pa FROM ProcessAttachment pa WHERE pa.fkProcess = ?1 AND pa.fileId = ?2")
    ProcessAttachment checkFileByProcess(ao.adnlogico.nuntius.multitenant.tenant.process.Process process, Long fileId);

    @Query("SELECT pa.name FROM ProcessAttachment pa WHERE pa.fkProcess = ?1 AND pa.fileId = ?2")
    String getUploadFilePath(ao.adnlogico.nuntius.multitenant.tenant.process.Process process, Long fileId);

}
