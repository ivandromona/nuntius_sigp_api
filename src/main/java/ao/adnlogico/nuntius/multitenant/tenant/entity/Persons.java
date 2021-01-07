/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.entity;

import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
import ao.adnlogico.nuntius.multitenant.tenant.user.Users;
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
@Table(name = "persons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persons.findAll", query = "SELECT p FROM Persons p"),
    @NamedQuery(name = "Persons.findById", query = "SELECT p FROM Persons p WHERE p.id = :id"),
    @NamedQuery(name = "Persons.findByFirstName", query = "SELECT p FROM Persons p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Persons.findByLastName", query = "SELECT p FROM Persons p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Persons.findByBirthdate", query = "SELECT p FROM Persons p WHERE p.birthdate = :birthdate"),
    @NamedQuery(name = "Persons.findByIdentityNumber", query = "SELECT p FROM Persons p WHERE p.identityNumber = :identityNumber"),
    @NamedQuery(name = "Persons.findByGender", query = "SELECT p FROM Persons p WHERE p.gender = :gender"),
    @NamedQuery(name = "Persons.findByCreatedAt", query = "SELECT p FROM Persons p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "Persons.findByUpdatedAt", query = "SELECT p FROM Persons p WHERE p.updatedAt = :updatedAt")})
public class Persons implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @Column(name = "identity_number")
    private String identityNumber;
    @Basic(optional = false)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkClaimantPerson")
    private Collection<Process> processCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPerson")
    private Collection<Users> usersCollection;

    public Persons()
    {
    }

    public Persons(Integer id)
    {
        this.id = id;
    }

    public Persons(Integer id, String firstName, String lastName, Date birthdate, String identityNumber, String gender, Date createdAt, Date updatedAt)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.identityNumber = identityNumber;
        this.gender = gender;
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

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Date getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getIdentityNumber()
    {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber)
    {
        this.identityNumber = identityNumber;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
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
    public Collection<Users> getUsersCollection()
    {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection)
    {
        this.usersCollection = usersCollection;
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
        if (!(object instanceof Persons)) {
            return false;
        }
        Persons other = (Persons) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Persons[ id=" + id + " ]";
    }

}
