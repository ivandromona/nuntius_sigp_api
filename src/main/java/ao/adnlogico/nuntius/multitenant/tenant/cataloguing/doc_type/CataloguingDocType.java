/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.doc_type;

import ao.adnlogico.nuntius.multitenant.tenant.department.Department;
import ao.adnlogico.nuntius.multitenant.tenant.cataloguing.dictionary.CataloguingDictionary;
import ao.adnlogico.nuntius.multitenant.tenant.cataloguing.Cataloguing;
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
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "cataloguing_doc_types")
@Data
@EqualsAndHashCode
@ToString
public class CataloguingDocType implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_department", referencedColumnName = "id")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDocType")
    private Collection<Cataloguing> cataloguings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDocType")
    private Collection<CataloguingDictionary> cataloguingDictionaries;

}
