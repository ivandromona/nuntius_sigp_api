/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.cataloguing;

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
public class CataloguingModelAssembler implements RepresentationModelAssembler<Cataloguing, EntityModel<Cataloguing>>
{

    @Override
    public EntityModel<Cataloguing> toModel(Cataloguing cataloguing)
    {

        return EntityModel.of(cataloguing, //
            linkTo(methodOn(CataloguingController.class).findById(cataloguing.getId())).withSelfRel(),
            linkTo(methodOn(CataloguingController.class).all()).withRel("provinces"));
    }
}
