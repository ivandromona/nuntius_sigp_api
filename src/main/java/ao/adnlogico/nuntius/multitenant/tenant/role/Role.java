/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.role;

import ao.adnlogico.nuntius.multitenant.tenant.roletype.RoleType;
import ao.adnlogico.nuntius.multitenant.tenant.user.User;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "create_process")
    private boolean createProcess;
    @Basic(optional = false)
    @Column(name = "edit_process")
    private boolean editProcess;
    @Basic(optional = false)
    @Column(name = "delete_process")
    private boolean deleteProcess;
    @Basic(optional = false)
    @Column(name = "view_process")
    private boolean viewProcess;
    @Basic(optional = false)
    @Column(name = "approve")
    private boolean approve;
    @Basic(optional = false)
    @Column(name = "reject")
    private boolean reject;
    @Basic(optional = false)
    @Column(name = "forward")
    private boolean forward;
    @Basic(optional = false)
    @Column(name = "comment")
    private boolean comment;
    @Basic(optional = false)
    @Column(name = "add_attach")
    private boolean addAttach;
    @Basic(optional = false)
    @Column(name = "remove_attach")
    private boolean removeAttach;
    @Basic(optional = false)
    @Column(name = "view_attach")
    private boolean viewAttach;
    @Basic(optional = false)
    @Column(name = "view_doc")
    private boolean viewDoc;
    @Basic(optional = false)
    @Column(name = "edit_doc")
    private boolean editDoc;
    @Basic(optional = false)
    @Column(name = "add_doc")
    private boolean addDoc;
    @JoinColumn(name = "fk_role_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RoleType fkRoleType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkRole")
    private Collection<User> usersCollection;

    public Role()
    {
    }

    public Role(Integer id)
    {
        this.id = id;
    }

    public Role(Integer id, String name, String description, boolean createProcess, boolean editProcess, boolean deleteProcess, boolean viewProcess, boolean approve, boolean reject, boolean forward, boolean comment, boolean addAttach, boolean removeAttach, boolean viewAttach, boolean viewDoc, boolean editDoc, boolean addDoc)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createProcess = createProcess;
        this.editProcess = editProcess;
        this.deleteProcess = deleteProcess;
        this.viewProcess = viewProcess;
        this.approve = approve;
        this.reject = reject;
        this.forward = forward;
        this.comment = comment;
        this.addAttach = addAttach;
        this.removeAttach = removeAttach;
        this.viewAttach = viewAttach;
        this.viewDoc = viewDoc;
        this.editDoc = editDoc;
        this.addDoc = addDoc;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean getCreateProcess()
    {
        return createProcess;
    }

    public void setCreateProcess(boolean createProcess)
    {
        this.createProcess = createProcess;
    }

    public boolean getEditProcess()
    {
        return editProcess;
    }

    public void setEditProcess(boolean editProcess)
    {
        this.editProcess = editProcess;
    }

    public boolean getDeleteProcess()
    {
        return deleteProcess;
    }

    public void setDeleteProcess(boolean deleteProcess)
    {
        this.deleteProcess = deleteProcess;
    }

    public boolean getViewProcess()
    {
        return viewProcess;
    }

    public void setViewProcess(boolean viewProcess)
    {
        this.viewProcess = viewProcess;
    }

    public boolean getApprove()
    {
        return approve;
    }

    public void setApprove(boolean approve)
    {
        this.approve = approve;
    }

    public boolean getReject()
    {
        return reject;
    }

    public void setReject(boolean reject)
    {
        this.reject = reject;
    }

    public boolean getForward()
    {
        return forward;
    }

    public void setForward(boolean forward)
    {
        this.forward = forward;
    }

    public boolean getComment()
    {
        return comment;
    }

    public void setComment(boolean comment)
    {
        this.comment = comment;
    }

    public boolean getAddAttach()
    {
        return addAttach;
    }

    public void setAddAttach(boolean addAttach)
    {
        this.addAttach = addAttach;
    }

    public boolean getRemoveAttach()
    {
        return removeAttach;
    }

    public void setRemoveAttach(boolean removeAttach)
    {
        this.removeAttach = removeAttach;
    }

    public boolean getViewAttach()
    {
        return viewAttach;
    }

    public void setViewAttach(boolean viewAttach)
    {
        this.viewAttach = viewAttach;
    }

    public boolean getViewDoc()
    {
        return viewDoc;
    }

    public void setViewDoc(boolean viewDoc)
    {
        this.viewDoc = viewDoc;
    }

    public boolean getEditDoc()
    {
        return editDoc;
    }

    public void setEditDoc(boolean editDoc)
    {
        this.editDoc = editDoc;
    }

    public boolean getAddDoc()
    {
        return addDoc;
    }

    public void setAddDoc(boolean addDoc)
    {
        this.addDoc = addDoc;
    }

    public RoleType getFkRoleType()
    {
        return fkRoleType;
    }

    public void setFkRoleType(RoleType fkRoleType)
    {
        this.fkRoleType = fkRoleType;
    }

    @XmlTransient
    public Collection<User> getUsersCollection()
    {
        return usersCollection;
    }

    public void setUsersCollection(Collection<User> usersCollection)
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
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Roles[ id=" + id + " ]";
    }

}
