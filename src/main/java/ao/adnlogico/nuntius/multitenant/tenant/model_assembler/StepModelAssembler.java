/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.model_assembler;

import ao.adnlogico.nuntius.multitenant.controller.StepController;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Step;
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
public class StepModelAssembler implements RepresentationModelAssembler<Step, EntityModel<Step>>
{

    @Override
    public EntityModel<Step> toModel(Step process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(StepController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(StepController.class).all()).withRel("provinces"));
    }
}
