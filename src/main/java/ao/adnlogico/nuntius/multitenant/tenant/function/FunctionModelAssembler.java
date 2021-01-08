/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.function;

import ao.adnlogico.nuntius.multitenant.tenant.department.*;
import ao.adnlogico.nuntius.multitenant.tenant.category.*;
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
    public EntityModel<Function> toModel(Function process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(FunctionController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(FunctionController.class).all()).withRel("provinces"));
    }
}
