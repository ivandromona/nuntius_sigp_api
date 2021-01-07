/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.user;

import ao.adnlogico.nuntius.multitenant.tenant.entity.Comments;
import ao.adnlogico.nuntius.multitenant.tenant.entity.DocTemplates;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Documents;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Forwarding;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Functions;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Messages;
import ao.adnlogico.nuntius.multitenant.tenant.entity.NotificationUsers;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Persons;
import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Roles;
import ao.adnlogico.nuntius.multitenant.tenant.progress.Progress;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByPhoneAlt", query = "SELECT u FROM Users u WHERE u.phoneAlt = :phoneAlt"),
    @NamedQuery(name = "Users.findByMechanographicNumber", query = "SELECT u FROM Users u WHERE u.mechanographicNumber = :mechanographicNumber"),
    @NamedQuery(name = "Users.findByDescription", query = "SELECT u FROM Users u WHERE u.description = :description"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByPerfilPicture", query = "SELECT u FROM Users u WHERE u.perfilPicture = :perfilPicture"),
    @NamedQuery(name = "Users.findByFile", query = "SELECT u FROM Users u WHERE u.file = :file"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt")})
public class Users implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Basic(optional = false)
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Basic(optional = false)
    @Column(name = "phone", unique = true)
    private String phone;
    @Basic(optional = false)
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
    private Collection<Comments> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUser")
    private Collection<Documents> documentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUser")
    private Collection<Forwarding> forwardingCollection;
    @JoinColumn(name = "fk_function", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Functions fkFunction;
    @JoinColumn(name = "fk_person", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Persons fkPerson;
    @JoinColumn(name = "fk_role", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Roles fkRole;
    @Size(max = 10)
    @Column(name = "status", nullable = false)
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<DocTemplates> docTemplatesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private Collection<Messages> messagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private Collection<Messages> messagesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUser")
    private Collection<Progress> progressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<NotificationUsers> notificationUsersCollection;

    public Users()
    {
    }

    public Users(Integer id)
    {
        this.id = id;
    }

    public Users(Integer id, String email, String phone, String phoneAlt, String mechanographicNumber, String description, String password, Date createdAt, Date updatedAt)
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

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public Users setPassword(String password)
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
    public Collection<Process> getProcessCollection1()
    {
        return processCollection1;
    }

    public void setProcessCollection1(Collection<Process> processCollection1)
    {
        this.processCollection1 = processCollection1;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection()
    {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection)
    {
        this.commentsCollection = commentsCollection;
    }

    @XmlTransient
    public Collection<Documents> getDocumentsCollection()
    {
        return documentsCollection;
    }

    public void setDocumentsCollection(Collection<Documents> documentsCollection)
    {
        this.documentsCollection = documentsCollection;
    }

    @XmlTransient
    public Collection<Forwarding> getForwardingCollection()
    {
        return forwardingCollection;
    }

    public void setForwardingCollection(Collection<Forwarding> forwardingCollection)
    {
        this.forwardingCollection = forwardingCollection;
    }

    public Functions getFkFunction()
    {
        return fkFunction;
    }

    public void setFkFunction(Functions fkFunction)
    {
        this.fkFunction = fkFunction;
    }

    public Persons getFkPerson()
    {
        return fkPerson;
    }

    public void setFkPerson(Persons fkPerson)
    {
        this.fkPerson = fkPerson;
    }

    public Roles getFkRole()
    {
        return fkRole;
    }

    public void setFkRole(Roles fkRole)
    {
        this.fkRole = fkRole;
    }

    public String getStatus()
    {
        return status;
    }

    public Users setStatus(String status)
    {
        this.status = status;
        return this;
    }

    public String getUserName()
    {
        return this.email;
    }

    public Users setUserName(String email)
    {
        this.email = email;
        return this;
    }

    @XmlTransient
    public Collection<DocTemplates> getDocTemplatesCollection()
    {
        return docTemplatesCollection;
    }

    public void setDocTemplatesCollection(Collection<DocTemplates> docTemplatesCollection)
    {
        this.docTemplatesCollection = docTemplatesCollection;
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

    @XmlTransient
    public Collection<Messages> getMessagesCollection1()
    {
        return messagesCollection1;
    }

    public void setMessagesCollection1(Collection<Messages> messagesCollection1)
    {
        this.messagesCollection1 = messagesCollection1;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
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
