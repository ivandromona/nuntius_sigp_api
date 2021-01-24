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

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "process")
public class Process implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

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

    @JoinColumn(name = "fk_role_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RoleType fkRoleType;

    @JoinColumn(name = "fk_claimant_entity", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entities fkClaimantEntity;

    @JoinColumn(name = "fk_approval", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Function fkApproval;

    @JoinColumn(name = "fk_operator_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User fkOperatorUser;

    @JoinColumn(name = "fk_responsible_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User fkResponsibleUser;

    @JoinColumn(name = "fk_claimant_person", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person fkClaimantPerson;

    @JoinColumn(name = "fk_category", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category fkCategory;

    @JoinColumn(name = "fk_explorer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Explorer fkExplorer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProcess")
    private Collection<Comment> commentsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProcess")
    private Collection<Document> documentsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProcess")
    private Collection<Forwarding> forwardingsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProcess")
    private Collection<ProcessAttachment> processAttachmentsCollection;

    @JoinTable(name = "process_progress", joinColumns
               = @JoinColumn(name = "fk_process", referencedColumnName = "id"), inverseJoinColumns
               = @JoinColumn(name = "fk_step", referencedColumnName = "id"))
    @ManyToMany
    private Collection<Step> stepsCollection;

    public Process()
    {
    }

    public Process(Long id)
    {
        this.id = id;
    }

    public Process(Long id, String origin, Date originDate, Date deadline, String processNumber, String externalReference, String subject, short confidential, String status, short filed, Date createdAt, Date updatedAt, String processType, short existing)
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

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
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

    @JsonIgnore
    public Collection<Step> getStepsCollection()
    {
        return stepsCollection;
    }

    public void setStepsCollection(Collection<Step> stepsCollection)
    {
        this.stepsCollection = stepsCollection;
    }

    @JsonIgnore
    public Collection<ProcessAttachment> getProcessAttachmentsCollection()
    {
        return processAttachmentsCollection;
    }

    public void setProcessAttachmentsCollection(Collection<ProcessAttachment> processAttachmentsCollection)
    {
        this.processAttachmentsCollection = processAttachmentsCollection;
    }

    public Function getFkApproval()
    {
        return fkApproval;
    }

    public void setFkApproval(Function fkApproval)
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

    public Person getFkClaimantPerson()
    {
        return fkClaimantPerson;
    }

    public void setFkClaimantPerson(Person fkClaimantPerson)
    {
        this.fkClaimantPerson = fkClaimantPerson;
    }

    public Explorer getFkExplorer()
    {
        return fkExplorer;
    }

    public void setFkExplorer(Explorer fkExplorer)
    {
        this.fkExplorer = fkExplorer;
    }

    public User getFkOperatorUser()
    {
        return fkOperatorUser;
    }

    public void setFkOperatorUser(User fkOperatorUser)
    {
        this.fkOperatorUser = fkOperatorUser;
    }

    public User getFkResponsibleUser()
    {
        return fkResponsibleUser;
    }

    public void setFkResponsibleUser(User fkResponsibleUser)
    {
        this.fkResponsibleUser = fkResponsibleUser;
    }

    public RoleType getFkRoleType()
    {
        return fkRoleType;
    }

    public void setFkRoleType(RoleType fkRoleType)
    {
        this.fkRoleType = fkRoleType;
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
    public Collection<Forwarding> getForwardingsCollection()
    {
        return forwardingsCollection;
    }

    public void setForwardingsCollection(Collection<Forwarding> forwardingsCollection)
    {
        this.forwardingsCollection = forwardingsCollection;
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
