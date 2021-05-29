/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.doc_type;

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
public class CataloguingDocTypeModelAssembler implements RepresentationModelAssembler<CataloguingDocType, EntityModel<CataloguingDocType>>
{

    @Override
    public EntityModel<CataloguingDocType> toModel(CataloguingDocType cataloguingDocType)
    {

        return EntityModel.of(cataloguingDocType, //
            linkTo(methodOn(CataloguingDocTypeController.class).findById(cataloguingDocType.getId())).withSelfRel(),
            linkTo(methodOn(CataloguingDocTypeController.class).all()).withRel("provinces"));
    }
}
