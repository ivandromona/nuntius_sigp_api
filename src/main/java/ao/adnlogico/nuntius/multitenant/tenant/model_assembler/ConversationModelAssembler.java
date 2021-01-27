/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.model_assembler;

import ao.adnlogico.nuntius.multitenant.tenant.entity.Conversation;
import ao.adnlogico.nuntius.multitenant.controller.ConversationController;
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
public class ConversationModelAssembler implements RepresentationModelAssembler<Conversation, EntityModel<Conversation>>
{

    @Override
    public EntityModel<Conversation> toModel(Conversation process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(ConversationController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(ConversationController.class).all()).withRel("provinces"));
    }
}