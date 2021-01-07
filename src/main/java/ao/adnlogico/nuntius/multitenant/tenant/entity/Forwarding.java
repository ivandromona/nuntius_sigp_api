/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.entity;

import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
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
@Table(name = "forwarding")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Forwarding.findAll", query = "SELECT f FROM Forwarding f"),
    @NamedQuery(name = "Forwarding.findById", query = "SELECT f FROM Forwarding f WHERE f.id = :id"),
    @NamedQuery(name = "Forwarding.findByDispatch", query = "SELECT f FROM Forwarding f WHERE f.dispatch = :dispatch"),
    @NamedQuery(name = "Forwarding.findByCreatedAt", query = "SELECT f FROM Forwarding f WHERE f.createdAt = :createdAt"),
    @NamedQuery(name = "Forwarding.findByAction", query = "SELECT f FROM Forwarding f WHERE f.action = :action")})
public class Forwarding implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dispatch")
    private String dispatch;
    @Lob
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "action")
    private String action;
    @JoinColumn(name = "fk_previous_step", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Steps fkPreviousStep;
    @JoinColumn(name = "fk_processo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Process fkProcesso;
    @JoinColumn(name = "fk_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users fkUser;

    public Forwarding()
    {
    }

    public Forwarding(Integer id)
    {
        this.id = id;
    }

    public Forwarding(Integer id, String dispatch, Date createdAt, String action)
    {
        this.id = id;
        this.dispatch = dispatch;
        this.createdAt = createdAt;
        this.action = action;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getDispatch()
    {
        return dispatch;
    }

    public void setDispatch(String dispatch)
    {
        this.dispatch = dispatch;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public Steps getFkPreviousStep()
    {
        return fkPreviousStep;
    }

    public void setFkPreviousStep(Steps fkPreviousStep)
    {
        this.fkPreviousStep = fkPreviousStep;
    }

    public Process getFkProcesso()
    {
        return fkProcesso;
    }

    public void setFkProcesso(Process fkProcesso)
    {
        this.fkProcesso = fkProcesso;
    }

    public Users getFkUser()
    {
        return fkUser;
    }

    public void setFkUser(Users fkUser)
    {
        this.fkUser = fkUser;
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
        if (!(object instanceof Forwarding)) {
            return false;
        }
        Forwarding other = (Forwarding) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Forwarding[ id=" + id + " ]";
    }

}
