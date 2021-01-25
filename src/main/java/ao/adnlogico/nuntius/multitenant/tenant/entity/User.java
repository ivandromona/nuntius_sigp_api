/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "users")
public class User implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Basic(optional = false)
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Basic(optional = false)
    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "phone_alt", unique = true)
    private String phoneAlt;
    @Basic(optional = false)
    @Column(name = "mechanographic_number", unique = true)
    private String mechanographicNumber;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "perfil_picture")
    private String perfilPicture;
    @Column(name = "file")
    private String file;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkOperatorUser")
    private Collection<Process> processCollection;

    @OneToMany(mappedBy = "fkResponsibleUser")
    private Collection<Process> processCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUser")
    private Collection<Comment> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUser")
    private Collection<Document> documentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUser")
    private Collection<Forwarding> forwardingCollection;
    @JoinColumn(name = "fk_function", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Function fkFunction;
    @JoinColumn(name = "fk_person", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person fkPerson;
    @JoinColumn(name = "fk_role", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role fkRole;
    @Size(max = 10)
    @Column(name = "status", nullable = false)
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<DocTemplate> docTemplatesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private Collection<Message> messagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private Collection<Message> messagesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUser")
    private Collection<Progress> progressCollection;

    @JoinTable(
            name = "user_notifications",
            joinColumns = @JoinColumn(name = "fk_user"),
            inverseJoinColumns = @JoinColumn(name = "fk_notification"))
    @ManyToMany
    private Collection<Notification> notifications;

    public User()
    {
    }

    public User(Long id)
    {
        this.id = id;
    }

    public User(Long id, String email, String phone, String phoneAlt, String mechanographicNumber, String description, String password, Date createdAt, Date updatedAt)
    {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.phoneAlt = phoneAlt;
        this.mechanographicNumber = mechanographicNumber;
        this.description = description;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhoneAlt()
    {
        return phoneAlt;
    }

    public void setPhoneAlt(String phoneAlt)
    {
        this.phoneAlt = phoneAlt;
    }

    public String getMechanographicNumber()
    {
        return mechanographicNumber;
    }

    public void setMechanographicNumber(String mechanographicNumber)
    {
        this.mechanographicNumber = mechanographicNumber;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPassword()
    {
        return password;
    }

    public User setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public String getPerfilPicture()
    {
        return perfilPicture;
    }

    public void setPerfilPicture(String perfilPicture)
    {
        this.perfilPicture = perfilPicture;
    }

    public String getFile()
    {
        return file;
    }

    public void setFile(String file)
    {
        this.file = file;
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
    public Collection<Process> getProcessCollection1()
    {
        return processCollection1;
    }

    public void setProcessCollection1(Collection<Process> processCollection1)
    {
        this.processCollection1 = processCollection1;
    }

    @JsonIgnore
    public Collection<Comment> getCommentsCollection()
    {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comment> commentsCollection)
    {
        this.commentsCollection = commentsCollection;
    }

    @JsonIgnore
    public Collection<Document> getDocumentsCollection()
    {
        return documentsCollection;
    }

    public void setDocumentsCollection(Collection<Document> documentsCollection)
    {
        this.documentsCollection = documentsCollection;
    }

    @JsonIgnore
    public Collection<Forwarding> getForwardingCollection()
    {
        return forwardingCollection;
    }

    public void setForwardingCollection(Collection<Forwarding> forwardingCollection)
    {
        this.forwardingCollection = forwardingCollection;
    }

    public Function getFkFunction()
    {
        return fkFunction;
    }

    public void setFkFunction(Function fkFunction)
    {
        this.fkFunction = fkFunction;
    }

    public Person getFkPerson()
    {
        return fkPerson;
    }

    public void setFkPerson(Person fkPerson)
    {
        this.fkPerson = fkPerson;
    }

    public Role getFkRole()
    {
        return fkRole;
    }

    public void setFkRole(Role fkRole)
    {
        this.fkRole = fkRole;
    }

    public String getStatus()
    {
        return status;
    }

    public User setStatus(String status)
    {
        this.status = status;
        return this;
    }

    public String getUserName()
    {
        return this.email;
    }

    public User setUserName(String email)
    {
        this.email = email;
        return this;
    }

    @JsonIgnore
    public Collection<DocTemplate> getDocTemplatesCollection()
    {
        return docTemplatesCollection;
    }

    public void setDocTemplatesCollection(Collection<DocTemplate> docTemplatesCollection)
    {
        this.docTemplatesCollection = docTemplatesCollection;
    }

    @JsonIgnore
    public Collection<Message> getMessagesCollection()
    {
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Message> messagesCollection)
    {
        this.messagesCollection = messagesCollection;
    }

    @JsonIgnore
    public Collection<Message> getMessagesCollection1()
    {
        return messagesCollection1;
    }

    public void setMessagesCollection1(Collection<Message> messagesCollection1)
    {
        this.messagesCollection1 = messagesCollection1;
    }

    @JsonIgnore
    public Collection<Progress> getProgressCollection()
    {
        return progressCollection;
    }

    public void setProgressCollection(Collection<Progress> progressCollection)
    {
        this.progressCollection = progressCollection;
    }

    @JsonIgnore
    public Collection<Notification> getNotifications()
    {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notificationUsersCollection)
    {
        this.notifications = notificationUsersCollection;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Users[ id=" + id + " ]";
    }

}
