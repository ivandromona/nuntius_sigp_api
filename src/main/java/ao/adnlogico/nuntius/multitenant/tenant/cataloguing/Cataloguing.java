/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.cataloguing;

import ao.adnlogico.nuntius.multitenant.tenant.cataloguing.catalog_data.CataloguingData;
import ao.adnlogico.nuntius.multitenant.tenant.cataloguing.doc_type.CataloguingDocType;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "cataloguing")
@Data
@EqualsAndHashCode
@ToString
public class Cataloguing implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "reference")
    private String reference;

    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_doc_type", referencedColumnName = "id")
    private CataloguingDocType fkDocType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCataloguing")
    private Collection<CataloguingData> cataloguingDatas;
}
