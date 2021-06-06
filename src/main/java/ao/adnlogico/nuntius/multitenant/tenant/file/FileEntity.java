/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.file;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.hateoas.server.core.Relation;

/**
 *
 * @author domingos.fernando
 */
@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "files")
@Relation(value = "fileEntity", collectionRelation = "fileEntityList")
public class FileEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_entity_id")
    private Long fkEntityId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_entity")
    private String fileEntity;

    @Column(name = "file_format")
    private String fileFormat;

    @Column(name = "upload_dir")
    private String uploadDir;

    @Column(name = "file_extention")
    private String fileExtention;

    public FileEntity()
    {
    }

    public FileEntity(String fileName, String fileType, String fileFormat, String uploadDir)
    {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileFormat = fileFormat;
        this.uploadDir = uploadDir;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getFkEntityId()
    {
        return fkEntityId;
    }

    public void setFkEntityId(Long fkEntityId)
    {
        this.fkEntityId = fkEntityId;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public String getFileFormat()
    {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat)
    {
        this.fileFormat = fileFormat;
    }

    public String getUploadDir()
    {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir)
    {
        this.uploadDir = uploadDir;
    }

    public String getFileEntity()
    {
        return fileEntity;
    }

    public void setFileEntity(String fileEntity)
    {
        this.fileEntity = fileEntity;
    }

    public String getFileExtention()
    {
        return fileExtention;
    }

    public void setFileExtention(String fileExtention)
    {
        this.fileExtention = fileExtention;
    }

    @Override
    public String toString()
    {
        return "FileEntity{" + "id=" + id + ", user=" + fkEntityId + ", fileName=" + fileName + ", fileType=" + fileType + ", fileFormat=" + fileFormat + ", uploadDir=" + uploadDir + '}';
    }

}
