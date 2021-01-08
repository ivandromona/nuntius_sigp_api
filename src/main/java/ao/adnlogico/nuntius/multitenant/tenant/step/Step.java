/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.step;

import ao.adnlogico.nuntius.multitenant.tenant.forwarding.Forwarding;
import ao.adnlogico.nuntius.multitenant.tenant.progress.Progress;
import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "steps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Steps.findAll", query = "SELECT s FROM Steps s"),
    @NamedQuery(name = "Steps.findById", query = "SELECT s FROM Steps s WHERE s.id = :id"),
    @NamedQuery(name = "Steps.findByName", query = "SELECT s FROM Steps s WHERE s.name = :name"),
    @NamedQuery(name = "Steps.findBySortOrder", query = "SELECT s FROM Steps s WHERE s.sortOrder = :sortOrder"),
    @NamedQuery(name = "Steps.findByDescriprions", query = "SELECT s FROM Steps s WHERE s.descriprions = :descriprions")})
public class Step implements Serializable
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
    @Column(name = "sort_order")
    private int sortOrder;
    @Basic(optional = false)
    @Column(name = "descriprions")
    private String descriprions;
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

    public Step(Integer id)
    {
        this.id = id;
    }

    public Step(Integer id, String name, int sortOrder, String descriprions)
    {
        this.id = id;
        this.name = name;
        this.sortOrder = sortOrder;
        this.descriprions = descriprions;
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

    public int getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public String getDescriprions()
    {
        return descriprions;
    }

    public void setDescriprions(String descriprions)
    {
        this.descriprions = descriprions;
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
    public Collection<Forwarding> getForwardingCollection()
    {
        return forwardingCollection;
    }

    public void setForwardingCollection(Collection<Forwarding> forwardingCollection)
    {
        this.forwardingCollection = forwardingCollection;
    }

    @XmlTransient
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
