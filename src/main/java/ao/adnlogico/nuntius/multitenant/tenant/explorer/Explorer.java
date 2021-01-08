/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.explorer;

import ao.adnlogico.nuntius.multitenant.tenant.department.Department;
import ao.adnlogico.nuntius.multitenant.tenant.entity.*;
import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
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
@Table(name = "explorers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Explorers.findAll", query = "SELECT e FROM Explorers e"),
    @NamedQuery(name = "Explorers.findById", query = "SELECT e FROM Explorers e WHERE e.id = :id"),
    @NamedQuery(name = "Explorers.findBySubject", query = "SELECT e FROM Explorers e WHERE e.subject = :subject"),
    @NamedQuery(name = "Explorers.findByType", query = "SELECT e FROM Explorers e WHERE e.type = :type"),
    @NamedQuery(name = "Explorers.findByCreatedAt", query = "SELECT e FROM Explorers e WHERE e.createdAt = :createdAt"),
    @NamedQuery(name = "Explorers.findByUpdatedAt", query = "SELECT e FROM Explorers e WHERE e.updatedAt = :updatedAt")})
public class Explorer implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "subject")
    private String subject;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkExplorer")
    private Collection<Process> processCollection;
    @JoinColumn(name = "fk_department", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department fkDepartment;
    @OneToMany(mappedBy = "fkExplorerFather")
    private Collection<Explorer> explorersCollection;
    @JoinColumn(name = "fk_explorer_father", referencedColumnName = "id")
    @ManyToOne
    private Explorer fkExplorerFather;

    public Explorer()
    {
    }

    public Explorer(Integer id)
    {
        this.id = id;
    }

    public Explorer(Integer id, String subject, String type, Date createdAt, Date updatedAt)
    {
        this.id = id;
        this.subject = subject;
        this.type = type;
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

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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
    public Collection<Process> getProcessCollection()
    {
        return processCollection;
    }

    public void setProcessCollection(Collection<Process> processCollection)
    {
        this.processCollection = processCollection;
    }

    public Department getFkDepartment()
    {
        return fkDepartment;
    }

    public void setFkDepartment(Department fkDepartment)
    {
        this.fkDepartment = fkDepartment;
    }

    @XmlTransient
    public Collection<Explorer> getExplorersCollection()
    {
        return explorersCollection;
    }

    public void setExplorersCollection(Collection<Explorer> explorersCollection)
    {
        this.explorersCollection = explorersCollection;
    }

    public Explorer getFkExplorerFather()
    {
        return fkExplorerFather;
    }

    public void setFkExplorerFather(Explorer fkExplorerFather)
    {
        this.fkExplorerFather = fkExplorerFather;
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
        if (!(object instanceof Explorer)) {
            return false;
        }
        Explorer other = (Explorer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Explorers[ id=" + id + " ]";
    }

}
