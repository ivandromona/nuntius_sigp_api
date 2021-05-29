/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.dictionary;

import ao.adnlogico.nuntius.multitenant.tenant.cataloguing.catalog_data.CataloguingData;
import ao.adnlogico.nuntius.multitenant.tenant.cataloguing.doc_type.CataloguingDocType;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "cataloguing_dictionaries")
@Data
@EqualsAndHashCode
@ToString
public class CataloguingDictionary implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "field_label")
    private String fieldLabel;

    @Basic(optional = false)
    @Column(name = "field_type")
    private String filedType;

    @Column(name = "possible_values", columnDefinition = "TEXT")
    private String possibleValues;

    @Basic(optional = false)
    @Column(name = "field_order")
    private Integer fieldOrder;

    @Basic(optional = false)
    @Column(name = "is_required")
    private Boolean isRequired;

    @Basic(optional = false)
    @Column(name = "is_multiple")
    private Boolean isMultiple;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_doc_type", referencedColumnName = "id")
    private CataloguingDocType fkDocType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCataloguingDictionary")
    private Collection<CataloguingData> cataloguingDatas;

}
