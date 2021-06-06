/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.notification;

import ao.adnlogico.nuntius.multitenant.tenant.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "notifications")
@JsonIgnoreProperties(value = {"users"}, allowSetters = true)
public class Notification implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Lob
    @Basic(optional = false)
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "entity")
    private String entity;
    @Basic(optional = false)
    @Column(name = "entity_id")
    private Long entityId;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "notifications")
    @JoinTable(
        name = "user_notifications",
        joinColumns = @JoinColumn(name = "fk_notification"),
        inverseJoinColumns = @JoinColumn(name = "fk_user"))
    @ManyToMany
    private Collection<User> users;

    public Notification()
    {
    }

    public Notification(Long id)
    {
        this.id = id;
    }

    public Notification(Long id, String content, String type, String entity, Long entityId, Date createdAt)
    {
        this.id = id;
        this.content = content;
        this.type = type;
        this.entity = entity;
        this.entityId = entityId;
        this.createdAt = createdAt;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getEntity()
    {
        return entity;
    }

    public void setEntity(String entity)
    {
        this.entity = entity;
    }

    public Long getEntityId()
    {
        return entityId;
    }

    public void setEntityId(Long entityId)
    {
        this.entityId = entityId;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Collection<User> getUsers()
    {
        return users;
    }

    public void setUsers(Collection<User> users)
    {
        this.users = users;
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
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Notifications[ id=" + id + " ]";
    }

}
