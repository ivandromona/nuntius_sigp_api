/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.step;

import ao.adnlogico.nuntius.multitenant.tenant.progress.Progress;
import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
import ao.adnlogico.nuntius.multitenant.tenant.forwarding.Forwarding;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "steps")
public class Step implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "sort_order")
    private int sortOrder;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @ManyToMany(mappedBy = "stepsCollection")
    private Collection<Process> processCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPreviousStep")
    private Collection<Forwarding> forwardingCollection;
    @OneToMany(mappedBy = "fkNextStep")
    private Collection<Step> stepsCollection;
    @JoinColumn(name = "fk_next_step", referencedColumnName = "id")
    @ManyToOne
    private Step fkNextStep;
    @JoinColumn(name = "fk_progress", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Progress fkProgress;

    public Step()
    {
    }

    public Step(Long id)
    {
        this.id = id;
    }

    public Step(Long id, String name, int sortOrder, String descriprions)
    {
        this.id = id;
        this.name = name;
        this.sortOrder = sortOrder;
        this.description = descriprions;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
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

    public int getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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
    public Collection<Forwarding> getForwardingCollection()
    {
        return forwardingCollection;
    }

    public void setForwardingCollection(Collection<Forwarding> forwardingCollection)
    {
        this.forwardingCollection = forwardingCollection;
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

    public Step getFkNextStep()
    {
        return fkNextStep;
    }

    public void setFkNextStep(Step fkNextStep)
    {
        this.fkNextStep = fkNextStep;
    }

    public Progress getFkProgress()
    {
        return fkProgress;
    }

    public void setFkProgress(Progress fkProgress)
    {
        this.fkProgress = fkProgress;
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
        if (!(object instanceof Step)) {
            return false;
        }
        Step other = (Step) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Steps[ id=" + id + " ]";
    }

}
