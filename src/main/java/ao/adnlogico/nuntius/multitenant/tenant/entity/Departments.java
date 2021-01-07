/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.entity;

import ao.adnlogico.nuntius.multitenant.tenant.progress.Progress;
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
import javax.persistence.Lob;
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
@Table(name = "departments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departments.findAll", query = "SELECT d FROM Departments d"),
    @NamedQuery(name = "Departments.findById", query = "SELECT d FROM Departments d WHERE d.id = :id"),
    @NamedQuery(name = "Departments.findByName", query = "SELECT d FROM Departments d WHERE d.name = :name"),
    @NamedQuery(name = "Departments.findByOrder", query = "SELECT d FROM Departments d WHERE d.order = :order")})
public class Departments implements Serializable
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
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "order")
    private int order;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDepartment")
    private Collection<Functions> functionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDepartment")
    private Collection<Explorers> explorersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDepartment")
    private Collection<Progress> progressCollection;
    @OneToMany(mappedBy = "fkDeptFather")
    private Collection<Departments> departmentsCollection;
    @JoinColumn(name = "fk_dept_father", referencedColumnName = "id")
    @ManyToOne
    private Departments fkDeptFather;

    public Departments()
    {
    }

    public Departments(Integer id)
    {
        this.id = id;
    }

    public Departments(Integer id, String name, String description, int order)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.order = order;
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

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    @XmlTransient
    public Collection<Functions> getFunctionsCollection()
    {
        return functionsCollection;
    }

    public void setFunctionsCollection(Collection<Functions> functionsCollection)
    {
        this.functionsCollection = functionsCollection;
    }

    @XmlTransient
    public Collection<Explorers> getExplorersCollection()
    {
        return explorersCollection;
    }

    public void setExplorersCollection(Collection<Explorers> explorersCollection)
    {
        this.explorersCollection = explorersCollection;
    }

    @XmlTransient
    public Collection<Progress> getProgressCollection()
    {
        return progressCollection;
    }

    public void setProgressCollection(Collection<Progress> progressCollection)
    {
        this.progressCollection = progressCollection;
    }

    @XmlTransient
    public Collection<Departments> getDepartmentsCollection()
    {
        return departmentsCollection;
    }

    public void setDepartmentsCollection(Collection<Departments> departmentsCollection)
    {
        this.departmentsCollection = departmentsCollection;
    }

    public Departments getFkDeptFather()
    {
        return fkDeptFather;
    }

    public void setFkDeptFather(Departments fkDeptFather)
    {
        this.fkDeptFather = fkDeptFather;
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
        if (!(object instanceof Departments)) {
            return false;
        }
        Departments other = (Departments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Departments[ id=" + id + " ]";
    }

}
