/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.entity;

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
@Table(name = "notifications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notifications.findAll", query = "SELECT n FROM Notifications n"),
    @NamedQuery(name = "Notifications.findById", query = "SELECT n FROM Notifications n WHERE n.id = :id"),
    @NamedQuery(name = "Notifications.findByContent", query = "SELECT n FROM Notifications n WHERE n.content = :content"),
    @NamedQuery(name = "Notifications.findByType", query = "SELECT n FROM Notifications n WHERE n.type = :type"),
    @NamedQuery(name = "Notifications.findByEntity", query = "SELECT n FROM Notifications n WHERE n.entity = :entity"),
    @NamedQuery(name = "Notifications.findByEntityId", query = "SELECT n FROM Notifications n WHERE n.entityId = :entityId"),
    @NamedQuery(name = "Notifications.findByCreatedAt", query = "SELECT n FROM Notifications n WHERE n.createdAt = :createdAt")})
public class Notifications implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "content")
    private int content;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "entity")
    private String entity;
    @Basic(optional = false)
    @Column(name = "entity_id")
    private int entityId;
    @Basic(optional = false)
    @Column(name = "created-at")
    private int createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notifications")
    private Collection<NotificationUsers> notificationUsersCollection;

    public Notifications()
    {
    }

    public Notifications(Integer id)
    {
        this.id = id;
    }

    public Notifications(Integer id, int content, String type, String entity, int entityId, int createdAt)
    {
        this.id = id;
        this.content = content;
        this.type = type;
        this.entity = entity;
        this.entityId = entityId;
        this.createdAt = createdAt;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public int getContent()
    {
        return content;
    }

    public void setContent(int content)
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

    public int getEntityId()
    {
        return entityId;
    }

    public void setEntityId(int entityId)
    {
        this.entityId = entityId;
    }

    public int getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(int createdAt)
    {
        this.createdAt = createdAt;
    }

    @XmlTransient
    public Collection<NotificationUsers> getNotificationUsersCollection()
    {
        return notificationUsersCollection;
    }

    public void setNotificationUsersCollection(Collection<NotificationUsers> notificationUsersCollection)
    {
        this.notificationUsersCollection = notificationUsersCollection;
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
        if (!(object instanceof Notifications)) {
            return false;
        }
        Notifications other = (Notifications) object;
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
