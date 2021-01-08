/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.message;

import ao.adnlogico.nuntius.multitenant.tenant.conversations.Conversation;
import ao.adnlogico.nuntius.multitenant.tenant.user.Users;
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
@Table(name = "messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m"),
    @NamedQuery(name = "Messages.findById", query = "SELECT m FROM Messages m WHERE m.id = :id"),
    @NamedQuery(name = "Messages.findByIsReaded", query = "SELECT m FROM Messages m WHERE m.isReaded = :isReaded"),
    @NamedQuery(name = "Messages.findBySendedAt", query = "SELECT m FROM Messages m WHERE m.sendedAt = :sendedAt")})
public class Message implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @Column(name = "is_readed")
    private boolean isReaded;
    @Basic(optional = false)
    @Column(name = "sended_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendedAt;
    @JoinColumn(name = "fk_conversation", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conversation fkConversation;
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users receiver;
    @JoinColumn(name = "sender", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users sender;

    public Message()
    {
    }

    public Message(Integer id)
    {
        this.id = id;
    }

    public Message(Integer id, String content, boolean isReaded, Date sendedAt)
    {
        this.id = id;
        this.content = content;
        this.isReaded = isReaded;
        this.sendedAt = sendedAt;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public boolean getIsReaded()
    {
        return isReaded;
    }

    public void setIsReaded(boolean isReaded)
    {
        this.isReaded = isReaded;
    }

    public Date getSendedAt()
    {
        return sendedAt;
    }

    public void setSendedAt(Date sendedAt)
    {
        this.sendedAt = sendedAt;
    }

    public Conversation getFkConversation()
    {
        return fkConversation;
    }

    public void setFkConversation(Conversation fkConversation)
    {
        this.fkConversation = fkConversation;
    }

    public Users getReceiver()
    {
        return receiver;
    }

    public void setReceiver(Users receiver)
    {
        this.receiver = receiver;
    }

    public Users getSender()
    {
        return sender;
    }

    public void setSender(Users sender)
    {
        this.sender = sender;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Messages[ id=" + id + " ]";
    }

}
