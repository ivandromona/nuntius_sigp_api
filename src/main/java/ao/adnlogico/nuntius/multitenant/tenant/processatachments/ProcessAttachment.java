/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.processatachments;

import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "process_attachments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcessAttachments.findAll", query = "SELECT p FROM ProcessAttachments p"),
    @NamedQuery(name = "ProcessAttachments.findById", query = "SELECT p FROM ProcessAttachments p WHERE p.id = :id"),
    @NamedQuery(name = "ProcessAttachments.findByName", query = "SELECT p FROM ProcessAttachments p WHERE p.name = :name"),
    @NamedQuery(name = "ProcessAttachments.findByDescription", query = "SELECT p FROM ProcessAttachments p WHERE p.description = :description"),
    @NamedQuery(name = "ProcessAttachments.findByFileUrl", query = "SELECT p FROM ProcessAttachments p WHERE p.fileUrl = :fileUrl"),
    @NamedQuery(name = "ProcessAttachments.findByFile", query = "SELECT p FROM ProcessAttachments p WHERE p.file = :file"),
    @NamedQuery(name = "ProcessAttachments.findByCreatedAt", query = "SELECT p FROM ProcessAttachments p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "ProcessAttachments.findByExtension", query = "SELECT p FROM ProcessAttachments p WHERE p.extension = :extension")})
public class ProcessAttachment implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "file_url")
    private String fileUrl;
    @Basic(optional = false)
    @Column(name = "file")
    private String file;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "extension")
    private String extension;
    @JoinColumn(name = "fk_process", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Process fkProcess;

    public ProcessAttachment()
    {
    }

    public ProcessAttachment(Integer id)
    {
        this.id = id;
    }

    public ProcessAttachment(Integer id, String name, String description, String fileUrl, String file, Date createdAt)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fileUrl = fileUrl;
        this.file = file;
        this.createdAt = createdAt;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getFileUrl()
    {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }

    public String getFile()
    {
        return file;
    }

    public void setFile(String file)
    {
        this.file = file;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getExtension()
    {
        return extension;
    }

    public void setExtension(String extension)
    {
        this.extension = extension;
    }

    public Process getFkProcess()
    {
        return fkProcess;
    }

    public void setFkProcess(Process fkProcess)
    {
        this.fkProcess = fkProcess;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcessAttachment)) {
            return false;
        }
        ProcessAttachment other = (ProcessAttachment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.ProcessAttachments[ id=" + id + " ]";
    }

}
