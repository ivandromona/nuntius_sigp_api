/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.category;

import ao.adnlogico.nuntius.multitenant.tenant.entity.DocTemplates;
import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id"),
    @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name"),
    @NamedQuery(name = "Category.findByDescription", query = "SELECT c FROM Category c WHERE c.description = :description"),
    @NamedQuery(name = "Category.findByDispatchTime", query = "SELECT c FROM Category c WHERE c.dispatchTime = :dispatchTime")})
public class Category implements Serializable
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
    private int description;
    @Basic(optional = false)
    @Column(name = "dispatch_time")
    private int dispatchTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCategory")
    private Collection<Process> processCollection;
    @JoinColumn(name = "fk_doc_template", referencedColumnName = "id")
    @ManyToOne
    private DocTemplates fkDocTemplate;

    public Category()
    {
    }

    public Category(Integer id)
    {
        this.id = id;
    }

    public Category(Integer id, String name, int description, int dispatchTime)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dispatchTime = dispatchTime;
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

    public int getDescription()
    {
        return description;
    }

    public void setDescription(int description)
    {
        this.description = description;
    }

    public int getDispatchTime()
    {
        return dispatchTime;
    }

    public void setDispatchTime(int dispatchTime)
    {
        this.dispatchTime = dispatchTime;
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

    public DocTemplates getFkDocTemplate()
    {
        return fkDocTemplate;
    }

    public void setFkDocTemplate(DocTemplates fkDocTemplate)
    {
        this.fkDocTemplate = fkDocTemplate;
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
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Category[ id=" + id + " ]";
    }

}
