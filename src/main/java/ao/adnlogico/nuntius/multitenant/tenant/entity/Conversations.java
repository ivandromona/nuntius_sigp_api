/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.entity;

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
@Table(name = "conversations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conversations.findAll", query = "SELECT c FROM Conversations c"),
    @NamedQuery(name = "Conversations.findById", query = "SELECT c FROM Conversations c WHERE c.id = :id"),
    @NamedQuery(name = "Conversations.findByKey", query = "SELECT c FROM Conversations c WHERE c.key = :key"),
    @NamedQuery(name = "Conversations.findByExisting", query = "SELECT c FROM Conversations c WHERE c.existing = :existing"),
    @NamedQuery(name = "Conversations.findByCreatedAt", query = "SELECT c FROM Conversations c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "Conversations.findByUpdatedAt", query = "SELECT c FROM Conversations c WHERE c.updatedAt = :updatedAt")})
public class Conversations implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "key")
    private String key;
    @Basic(optional = false)
    @Column(name = "existing")
    private short existing;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkConversation")
    private Collection<Messages> messagesCollection;

    public Conversations()
    {
    }

    public Conversations(Integer id)
    {
        this.id = id;
    }

    public Conversations(Integer id, String key, short existing, Date createdAt, Date updatedAt)
    {
        this.id = id;
        this.key = key;
        this.existing = existing;
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

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public short getExisting()
    {
        return existing;
    }

    public void setExisting(short existing)
    {
        this.existing = existing;
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
    public Collection<Messages> getMessagesCollection()
    {
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Messages> messagesCollection)
    {
        this.messagesCollection = messagesCollection;
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
        if (!(object instanceof Conversations)) {
            return false;
        }
        Conversations other = (Conversations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Conversations[ id=" + id + " ]";
    }

}
