/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.multitenant.nuntius.tenant.department;

import ao.adnlogico.multitenant.nuntius.tenant.explorer.Explorer;
import ao.adnlogico.multitenant.nuntius.tenant.function.Function;
import ao.adnlogico.multitenant.nuntius.tenant.progress.Progress;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "departments")
public class Department implements Serializable
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
    private Collection<Function> functionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDepartment")
    private Collection<Explorer> explorersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDepartment")
    private Collection<Progress> progressCollection;
    @OneToMany(mappedBy = "fkDeptFather")
    private Collection<Department> departmentsCollection;
    @JoinColumn(name = "fk_dept_father", referencedColumnName = "id")
    @ManyToOne
    private Department fkDeptFather;

    public Department()
    {
    }

    public Department(Integer id)
    {
        this.id = id;
    }

    public Department(Integer id, String name, String description, int order)
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
    public Collection<Function> getFunctionsCollection()
    {
        return functionsCollection;
    }

    public void setFunctionsCollection(Collection<Function> functionsCollection)
    {
        this.functionsCollection = functionsCollection;
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
    public Collection<Department> getDepartmentsCollection()
    {
        return departmentsCollection;
    }

    public void setDepartmentsCollection(Collection<Department> departmentsCollection)
    {
        this.departmentsCollection = departmentsCollection;
    }

    public Department getFkDeptFather()
    {
        return fkDeptFather;
    }

    public void setFkDeptFather(Department fkDeptFather)
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Department[ id=" + id + " ]";
    }

}
