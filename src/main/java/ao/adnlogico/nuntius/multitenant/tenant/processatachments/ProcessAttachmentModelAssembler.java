/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.processatachments;

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
public class ProcessAttachmentModelAssembler implements RepresentationModelAssembler<ProcessAttachment, EntityModel<ProcessAttachment>>
{

    @Override
    public EntityModel<ProcessAttachment> toModel(ProcessAttachment process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(ProcessAttachmentController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(ProcessAttachmentController.class).all()).withRel("provinces"));
    }
}
