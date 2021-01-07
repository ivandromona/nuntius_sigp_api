/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.entity;

import ao.adnlogico.nuntius.multitenant.tenant.user.Users;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "doc_templates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocTemplates.findAll", query = "SELECT d FROM DocTemplates d"),
    @NamedQuery(name = "DocTemplates.findById", query = "SELECT d FROM DocTemplates d WHERE d.id = :id"),
    @NamedQuery(name = "DocTemplates.findByName", query = "SELECT d FROM DocTemplates d WHERE d.name = :name"),
    @NamedQuery(name = "DocTemplates.findByFileUrl", query = "SELECT d FROM DocTemplates d WHERE d.fileUrl = :fileUrl"),
    @NamedQuery(name = "DocTemplates.findByDescription", query = "SELECT d FROM DocTemplates d WHERE d.description = :description"),
    @NamedQuery(name = "DocTemplates.findByCreatedAt", query = "SELECT d FROM DocTemplates d WHERE d.createdAt = :createdAt"),
    @NamedQuery(name = "DocTemplates.findByUpdatedAt", query = "SELECT d FROM DocTemplates d WHERE d.updatedAt = :updatedAt")})
public class DocTemplates implements Serializable
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
    @Lob
    @Column(name = "standard_content")
    private String standardContent;
    @Basic(optional = false)
    @Column(name = "file_url")
    private String fileUrl;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDocTemplate")
    private Collection<Documents> documentsCollection;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users createdBy;
    @OneToMany(mappedBy = "fkDocTemplate")
    private Collection<Categories> categoriesCollection;

    public DocTemplates()
    {
    }

    public DocTemplates(Integer id)
    {
        this.id = id;
    }

    public DocTemplates(Integer id, String name, String standardContent, String fileUrl, String description, Date createdAt, Date updatedAt)
    {
        this.id = id;
        this.name = name;
        this.standardContent = standardContent;
        this.fileUrl = fileUrl;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getStandardContent()
    {
        return standardContent;
    }

    public void setStandardContent(String standardContent)
    {
        this.standardContent = standardContent;
    }

    public String getFileUrl()
    {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    public Collection<Documents> getDocumentsCollection()
    {
        return documentsCollection;
    }

    public void setDocumentsCollection(Collection<Documents> documentsCollection)
    {
        this.documentsCollection = documentsCollection;
    }

    public Users getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy)
    {
        this.createdBy = createdBy;
    }

    @XmlTransient
    public Collection<Categories> getCategoriesCollection()
    {
        return categoriesCollection;
    }

    public void setCategoriesCollection(Collection<Categories> categoriesCollection)
    {
        this.categoriesCollection = categoriesCollection;
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
        if (!(object instanceof DocTemplates)) {
            return false;
        }
        DocTemplates other = (DocTemplates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.DocTemplates[ id=" + id + " ]";
    }

}
