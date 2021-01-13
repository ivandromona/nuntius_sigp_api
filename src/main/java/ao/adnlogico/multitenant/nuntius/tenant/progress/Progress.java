/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.multitenant.nuntius.tenant.progress;

import ao.adnlogico.multitenant.nuntius.tenant.department.Department;
import ao.adnlogico.multitenant.nuntius.tenant.step.Step;
import ao.adnlogico.multitenant.nuntius.tenant.user.User;
import io.swagger.annotations.ApiModel;
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
@Table(name = "progress")
@ApiModel(description = "All details about the Progress. ")
public class Progress implements Serializable
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
    @Column(name = "fk_parent")
    private int fkParent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProgress")
    private Collection<Step> stepsCollection;
    @JoinColumn(name = "fk_department", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department fkDepartment;
    @JoinColumn(name = "fk_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User fkUser;

    public Progress()
    {
    }

    public Progress(Integer id)
    {
        this.id = id;
    }

    public Progress(Integer id, String name, String description, int fkParent)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fkParent = fkParent;
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

    public int getFkParent()
    {
        return fkParent;
    }

    public void setFkParent(int fkParent)
    {
        this.fkParent = fkParent;
    }

    @XmlTransient
    public Collection<Step> getStepsCollection()
    {
        return stepsCollection;
    }

    public void setStepsCollection(Collection<Step> stepsCollection)
    {
        this.stepsCollection = stepsCollection;
    }

    public Department getFkDepartment()
    {
        return fkDepartment;
    }

    public void setFkDepartment(Department fkDepartment)
    {
        this.fkDepartment = fkDepartment;
    }

    public User getFkUser()
    {
        return fkUser;
    }

    public void setFkUser(User fkUser)
    {
        this.fkUser = fkUser;
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
        if (!(object instanceof Progress)) {
            return false;
        }
        Progress other = (Progress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Progress[ id=" + id + " ]";
    }

}
