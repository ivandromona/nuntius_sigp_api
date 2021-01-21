/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.model_assembler;

import ao.adnlogico.nuntius.multitenant.tenant.entity.Module;
import ao.adnlogico.nuntius.multitenant.controller.ModuleController;
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
public class ModuleModelAssembler implements RepresentationModelAssembler<Module, EntityModel<Module>>
{

    @Override
    public EntityModel<Module> toModel(Module process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(ModuleController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(ModuleController.class).all()).withRel("provinces"));
    }
}
