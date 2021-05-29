/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.catalog_data;

import ao.adnlogico.nuntius.multitenant.tenant.cataloguing.dictionary.CataloguingDictionary;
import ao.adnlogico.nuntius.multitenant.tenant.cataloguing.Cataloguing;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cataloguing_data")
@Data
@EqualsAndHashCode
@ToString
public class CataloguingData implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_dictionary")
    private CataloguingDictionary fkCataloguingDictionary;

    @ManyToOne
    @JoinColumn(name = "fk_cataloguing")
    private Cataloguing fkCataloguing;

    @NotNull
    @Basic(optional = false)
    @Column(name = "data_value", columnDefinition = "TEXT")
    private String value;

}
