/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.catalog_data;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author domingos.fernando
 */
@Component
public class CataloguingDataModelAssembler implements RepresentationModelAssembler<CataloguingData, EntityModel<CataloguingData>>
{

    @Override
    public EntityModel<CataloguingData> toModel(CataloguingData cataloguingData)
    {

        return EntityModel.of(cataloguingData, //
            linkTo(methodOn(CataloguingDataController.class).findById(cataloguingData.getId())).withSelfRel(),
            linkTo(methodOn(CataloguingDataController.class).all()).withRel("provinces"));
    }
}
