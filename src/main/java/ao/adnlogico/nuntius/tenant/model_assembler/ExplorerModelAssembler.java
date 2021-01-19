/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.tenant.model_assembler;

import ao.adnlogico.nuntius.tenant.controller.ExplorerController;
import ao.adnlogico.nuntius.tenant.entity.Explorer;
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
public class ExplorerModelAssembler implements RepresentationModelAssembler<Explorer, EntityModel<Explorer>>
{

    @Override
    public EntityModel<Explorer> toModel(Explorer process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(ExplorerController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(ExplorerController.class).all()).withRel("provinces"));
    }
}
