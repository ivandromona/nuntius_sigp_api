/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.progress;

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
public class ProgressModelAssembler implements RepresentationModelAssembler<Progress, EntityModel<Progress>>
{

    @Override
    public EntityModel<Progress> toModel(Progress process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(ProgressController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(ProgressController.class).all()).withRel("provinces"));
    }
}
