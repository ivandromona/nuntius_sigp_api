/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.module;

import ao.adnlogico.nuntius.multitenant.tenant.roletype.RoleType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "modules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modules.findAll", query = "SELECT m FROM Modules m"),
    @NamedQuery(name = "Modules.findById", query = "SELECT m FROM Modules m WHERE m.id = :id"),
    @NamedQuery(name = "Modules.findByName", query = "SELECT m FROM Modules m WHERE m.name = :name"),
    @NamedQuery(name = "Modules.findByKey", query = "SELECT m FROM Modules m WHERE m.key = :key"),
    @NamedQuery(name = "Modules.findByActive", query = "SELECT m FROM Modules m WHERE m.active = :active"),
    @NamedQuery(name = "Modules.findByIcon", query = "SELECT m FROM Modules m WHERE m.icon = :icon"),
    @NamedQuery(name = "Modules.findByCreatedAt", query = "SELECT m FROM Modules m WHERE m.createdAt = :createdAt"),
    @NamedQuery(name = "Modules.findByDeletedAt", query = "SELECT m FROM Modules m WHERE m.deletedAt = :deletedAt")})
public class Module implements Serializable
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
    @Column(name = "active")
    private boolean active;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "icon")
    private String icon;
    @Lob
    @Column(name = "link")
    private String link;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
    @JoinColumn(name = "fk_role_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RoleType fkRoleType;

    public Module()
    {
    }

    public Module(Integer id)
    {
        this.id = id;
    }

    public Module(Integer id, String name, String key, boolean active, Date createdAt, Date deletedAt)
    {
        this.id = id;
        this.name = name;
        this.key = key;
        this.active = active;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
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

    public boolean getActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt()
    {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt)
    {
        this.deletedAt = deletedAt;
    }

    public RoleType getFkRoleType()
    {
        return fkRoleType;
    }

    public void setFkRoleType(RoleType fkRoleType)
    {
        this.fkRoleType = fkRoleType;
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
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Modules[ id=" + id + " ]";
    }

}
