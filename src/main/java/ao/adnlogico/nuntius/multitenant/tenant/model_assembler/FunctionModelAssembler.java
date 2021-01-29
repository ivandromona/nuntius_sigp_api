/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.model_assembler;

import ao.adnlogico.nuntius.multitenant.tenant.function.Function;
import ao.adnlogico.nuntius.multitenant.controller.FunctionController;
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
public class FunctionModelAssembler implements RepresentationModelAssembler<Function, EntityModel<Function>>
{

    @Override
    public EntityModel<Function> toModel(Function function)
    {

        return EntityModel.of(function, //
            linkTo(methodOn(FunctionController.class).findById(function.getId())).withSelfRel(),
            linkTo(methodOn(FunctionController.class).all()).withRel("functions"));
    }
}
