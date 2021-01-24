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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "comments")
public class Comment implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(mappedBy = "fkMainComment")
    private Collection<Comment> commentsCollection;
    @JoinColumn(name = "fk_main_comment", referencedColumnName = "id")
    @ManyToOne
    private Comment fkMainComment;
    @JoinColumn(name = "fk_process", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Process fkProcess;
    @JoinColumn(name = "fk_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User fkUser;

    public Comment()
    {
    }

    public Comment(Long id)
    {
        this.id = id;
    }

    public Comment(Long id, String comment, Date createdAt)
    {
        this.id = id;
        this.comment = comment;
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

    @JsonIgnore
    public Collection<Comment> getCommentsCollection()
    {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comment> commentsCollection)
    {
        this.commentsCollection = commentsCollection;
    }

    public Comment getFkMainComment()
    {
        return fkMainComment;
    }

    public void setFkMainComment(Comment fkMainComment)
    {
        this.fkMainComment = fkMainComment;
    }

    public Process getFkProcess()
    {
        return fkProcess;
    }

    public void setFkProcess(Process fkProcess)
    {
        this.fkProcess = fkProcess;
    }

    public User getFkUser()
    {
        return fkUser;
    }

    public void setFkUser(User fkUser)
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Comments[ id=" + id + " ]";
    }

}
