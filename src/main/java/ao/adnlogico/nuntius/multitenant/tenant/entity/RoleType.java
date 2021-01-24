/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "role_types")
public class RoleType implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "role_key")
    private String roleKey;
    @Basic(optional = false)
    @Column(name = "weight")
    private short weight;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkRoleType")
    private Collection<Process> processCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkRoleType")
    private Collection<Role> rolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkRoleType")
    private Collection<Module> modulesCollection;

    public RoleType()
    {
    }

    public RoleType(Long id)
    {
        this.id = id;
    }

    public RoleType(Long id, String name, String key, short weight, String description)
    {
        this.id = id;
        this.name = name;
        this.roleKey = key;
        this.weight = weight;
        this.description = description;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
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

    public String getRoleKey()
    {
        return roleKey;
    }

    public void setRoleKey(String roleKey)
    {
        this.roleKey = roleKey;
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

    @JsonIgnore
    public Collection<Process> getProcessCollection()
    {
        return processCollection;
    }

    public void setProcessCollection(Collection<Process> processCollection)
    {
        this.processCollection = processCollection;
    }

    @JsonIgnore
    public Collection<Role> getRolesCollection()
    {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Role> rolesCollection)
    {
        this.rolesCollection = rolesCollection;
    }

    @JsonIgnore
    public Collection<Module> getModulesCollection()
    {
        return modulesCollection;
    }

    public void setModulesCollection(Collection<Module> modulesCollection)
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
        if (!(object instanceof RoleType)) {
            return false;
        }
        RoleType other = (RoleType) object;
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
