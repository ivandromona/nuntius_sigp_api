/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author domingos.fernando
 */
public interface FileRepository extends JpaRepository<FileEntity, Long>
{

    @Query("SELECT f FROM FileEntity f WHERE f.fkEntityId = ?1 AND fileType = ?2")
    FileEntity checkFileByUser(Long userId, String fileType);

    @Query("SELECT f.fileName FROM FileEntity f WHERE f.fkEntityId = ?1 AND f.fileType = ?2 ")
    String getUploadFilePath(Long fkEntityId, String fileType);

    @Query("SELECT f.fileName FROM FileEntity f WHERE f.fkEntityId = ?1 AND f.fileType = ?2 AND f.fileType = ?3")
    String getUploadFilePath(Long fkEntityId, String fileType, String fileEntity);

    @Query("SELECT f.fileName FROM FileEntity f WHERE f.fkEntityId = ?1 AND f.fileType = ?2 AND f.fileType = ?3")
    FileEntity checkFileByEntity(Long fkEntityId, String fileType, String fileEntity);
}
