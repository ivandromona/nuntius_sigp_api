/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.process;

import ao.adnlogico.nuntius.multitenant.tenant.category.Category;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Comments;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Documents;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Entities;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Explorers;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Forwarding;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Functions;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Persons;
import ao.adnlogico.nuntius.multitenant.tenant.entity.ProcessAttachments;
import ao.adnlogico.nuntius.multitenant.tenant.entity.RoleTypes;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Steps;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "process")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Process.findAll", query = "SELECT p FROM Process p"),
    @NamedQuery(name = "Process.findById", query = "SELECT p FROM Process p WHERE p.id = :id"),
    @NamedQuery(name = "Process.findByOrigin", query = "SELECT p FROM Process p WHERE p.origin = :origin"),
    @NamedQuery(name = "Process.findByOriginDate", query = "SELECT p FROM Process p WHERE p.originDate = :originDate"),
    @NamedQuery(name = "Process.findByDeadline", query = "SELECT p FROM Process p WHERE p.deadline = :deadline"),
    @NamedQuery(name = "Process.findByProcessNumber", query = "SELECT p FROM Process p WHERE p.processNumber = :processNumber"),
    @NamedQuery(name = "Process.findByExternalReference", query = "SELECT p FROM Process p WHERE p.externalReference = :externalReference"),
    @NamedQuery(name = "Process.findBySubject", query = "SELECT p FROM Process p WHERE p.subject = :subject"),
    @NamedQuery(name = "Process.findByDescription", query = "SELECT p FROM Process p WHERE p.description = :description"),
    @NamedQuery(name = "Process.findByConfidential", query = "SELECT p FROM Process p WHERE p.confidential = :confidential"),
    @NamedQuery(name = "Process.findByStatus", query = "SELECT p FROM Process p WHERE p.status = :status"),
    @NamedQuery(name = "Process.findByFiled", query = "SELECT p FROM Process p WHERE p.filed = :filed"),
    @NamedQuery(name = "Process.findByCreatedAt", query = "SELECT p FROM Process p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "Process.findByUpdatedAt", query = "SELECT p FROM Process p WHERE p.updatedAt = :updatedAt"),
    @NamedQuery(name = "Process.findByProcessType", query = "SELECT p FROM Process p WHERE p.processType = :processType"),
    @NamedQuery(name = "Process.findByExisting", query = "SELECT p FROM Process p WHERE p.existing = :existing"),
    @NamedQuery(name = "Process.findByClaimant", query = "SELECT p FROM Process p WHERE p.claimant = :claimant")})
public class Process implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "origin")
    private String origin;
    @Basic(optional = false)
    @Column(name = "origin_date")
    @Temporal(TemporalType.DATE)
    private Date originDate;
    @Basic(optional = false)
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Basic(optional = false)
    @Column(name = "process_number")
    private String processNumber;
    @Basic(optional = false)
    @Column(name = "external_reference")
    private String externalReference;
    @Basic(optional = false)
    @Column(name = "subject")
    private String subject;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "confidential")
    private short confidential;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "filed")
    private short filed;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "process_type")
    private String processType;
    @Basic(optional = false)
    @Column(name = "existing")
    private short existing;
    @Column(name = "claimant")
    private String claimant;
    @JoinTable(name = "process_progress", joinColumns = {
        @JoinColumn(name = "fk_process", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_step", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Steps> stepsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProcess")
    private Collection<ProcessAttachments> processAttachmentsCollection;
    @JoinColumn(name = "fk_approval", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Functions fkApproval;
    @JoinColumn(name = "fk_category", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category fkCategory;
    @JoinColumn(name = "fk_claimant_entity", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entities fkClaimantEntity;
    @JoinColumn(name = "fk_claimant_person", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Persons fkClaimantPerson;
    @JoinColumn(name = "fk_explorer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Explorers fkExplorer;
    @JoinColumn(name = "fk_operator_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users fkOperatorUser;
    @JoinColumn(name = "fk_responsible_user", referencedColumnName = "id")
    @ManyToOne
    private Users fkResponsibleUser;
    @JoinColumn(name = "fk_role_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RoleTypes fkRoleType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProcess")
    private Collection<Comments> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProcess")
    private Collection<Documents> documentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProcesso")
    private Collection<Forwarding> forwardingCollection;

    public Process()
    {
    }

    public Process(Integer id)
    {
        this.id = id;
    }

    public Process(Integer id, String origin, Date originDate, Date deadline, String processNumber, String externalReference, String subject, short confidential, String status, short filed, Date createdAt, Date updatedAt, String processType, short existing)
    {
        this.id = id;
        this.origin = origin;
        this.originDate = originDate;
        this.deadline = deadline;
        this.processNumber = processNumber;
        this.externalReference = externalReference;
        this.subject = subject;
        this.confidential = confidential;
        this.status = status;
        this.filed = filed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.processType = processType;
        this.existing = existing;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getOrigin()
    {
        return origin;
    }

    public void setOrigin(String origin)
    {
        this.origin = origin;
    }

    public Date getOriginDate()
    {
        return originDate;
    }

    public void setOriginDate(Date originDate)
    {
        this.originDate = originDate;
    }

    public Date getDeadline()
    {
        return deadline;
    }

    public void setDeadline(Date deadline)
    {
        this.deadline = deadline;
    }

    public String getProcessNumber()
    {
        return processNumber;
    }

    public void setProcessNumber(String processNumber)
    {
        this.processNumber = processNumber;
    }

    public String getExternalReference()
    {
        return externalReference;
    }

    public void setExternalReference(String externalReference)
    {
        this.externalReference = externalReference;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public short getConfidential()
    {
        return confidential;
    }

    public void setConfidential(short confidential)
    {
        this.confidential = confidential;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public short getFiled()
    {
        return filed;
    }

    public void setFiled(short filed)
    {
        this.filed = filed;
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

    public String getProcessType()
    {
        return processType;
    }

    public void setProcessType(String processType)
    {
        this.processType = processType;
    }

    public short getExisting()
    {
        return existing;
    }

    public void setExisting(short existing)
    {
        this.existing = existing;
    }

    public String getClaimant()
    {
        return claimant;
    }

    public void setClaimant(String claimant)
    {
        this.claimant = claimant;
    }

    @XmlTransient
    public Collection<Steps> getStepsCollection()
    {
        return stepsCollection;
    }

    public void setStepsCollection(Collection<Steps> stepsCollection)
    {
        this.stepsCollection = stepsCollection;
    }

    @XmlTransient
    public Collection<ProcessAttachments> getProcessAttachmentsCollection()
    {
        return processAttachmentsCollection;
    }

    public void setProcessAttachmentsCollection(Collection<ProcessAttachments> processAttachmentsCollection)
    {
        this.processAttachmentsCollection = processAttachmentsCollection;
    }

    public Functions getFkApproval()
    {
        return fkApproval;
    }

    public void setFkApproval(Functions fkApproval)
    {
        this.fkApproval = fkApproval;
    }

    public Category getFkCategory()
    {
        return fkCategory;
    }

    public void setFkCategory(Category fkCategory)
    {
        this.fkCategory = fkCategory;
    }

    public Entities getFkClaimantEntity()
    {
        return fkClaimantEntity;
    }

    public void setFkClaimantEntity(Entities fkClaimantEntity)
    {
        this.fkClaimantEntity = fkClaimantEntity;
    }

    public Persons getFkClaimantPerson()
    {
        return fkClaimantPerson;
    }

    public void setFkClaimantPerson(Persons fkClaimantPerson)
    {
        this.fkClaimantPerson = fkClaimantPerson;
    }

    public Explorers getFkExplorer()
    {
        return fkExplorer;
    }

    public void setFkExplorer(Explorers fkExplorer)
    {
        this.fkExplorer = fkExplorer;
    }

    public Users getFkOperatorUser()
    {
        return fkOperatorUser;
    }

    public void setFkOperatorUser(Users fkOperatorUser)
    {
        this.fkOperatorUser = fkOperatorUser;
    }

    public Users getFkResponsibleUser()
    {
        return fkResponsibleUser;
    }

    public void setFkResponsibleUser(Users fkResponsibleUser)
    {
        this.fkResponsibleUser = fkResponsibleUser;
    }

    public RoleTypes getFkRoleType()
    {
        return fkRoleType;
    }

    public void setFkRoleType(RoleTypes fkRoleType)
    {
        this.fkRoleType = fkRoleType;
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
        if (!(object instanceof Process)) {
            return false;
        }
        Process other = (Process) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Process[ id=" + id + " ]";
    }

}
