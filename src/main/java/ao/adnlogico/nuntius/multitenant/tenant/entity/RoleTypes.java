/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.entity;

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
@Table(name = "role_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleTypes.findAll", query = "SELECT r FROM RoleTypes r"),
    @NamedQuery(name = "RoleTypes.findById", query = "SELECT r FROM RoleTypes r WHERE r.id = :id"),
    @NamedQuery(name = "RoleTypes.findByName", query = "SELECT r FROM RoleTypes r WHERE r.name = :name"),
    @NamedQuery(name = "RoleTypes.findByKey", query = "SELECT r FROM RoleTypes r WHERE r.key = :key"),
    @NamedQuery(name = "RoleTypes.findByWeight", query = "SELECT r FROM RoleTypes r WHERE r.weight = :weight"),
    @NamedQuery(name = "RoleTypes.findByDescription", query = "SELECT r FROM RoleTypes r WHERE r.description = :description")})
public class RoleTypes implements Serializable
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
    @Column(name = "key")
    private String key;
    @Basic(optional = false)
    @Column(name = "weight")
    private short weight;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkRoleType")
    private Collection<Process> processCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkRoleType")
    private Collection<Roles> rolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkRoleType")
    private Collection<Modules> modulesCollection;

    public RoleTypes()
    {
    }

    public RoleTypes(Integer id)
    {
        this.id = id;
    }

    public RoleTypes(Integer id, String name, String key, short weight, String description)
    {
        this.id = id;
        this.name = name;
        this.key = key;
        this.weight = weight;
        this.description = description;
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

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public short getWeight()
    {
        return weight;
    }

    public void setWeight(short weight)
    {
        this.weight = weight;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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

    @XmlTransient
    public Collection<Roles> getRolesCollection()
    {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection)
    {
        this.rolesCollection = rolesCollection;
    }

    @XmlTransient
    public Collection<Modules> getModulesCollection()
    {
        return modulesCollection;
    }

    public void setModulesCollection(Collection<Modules> modulesCollection)
    {
        this.modulesCollection = modulesCollection;
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
        if (!(object instanceof RoleTypes)) {
            return false;
        }
        RoleTypes other = (RoleTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.RoleTypes[ id=" + id + " ]";
    }

}
