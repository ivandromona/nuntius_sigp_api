/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.process;

import ao.adnlogico.nuntius.multitenant.tenant.process.ProcessController;
import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
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
public class ProcessModelAssembler implements RepresentationModelAssembler<Process, EntityModel<Process>>
{

    @Override
    public EntityModel<Process> toModel(Process process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(ProcessController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(ProcessController.class).all()).withRel("provinces"));
    }
}
