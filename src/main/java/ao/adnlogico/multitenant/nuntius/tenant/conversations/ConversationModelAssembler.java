/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.multitenant.nuntius.tenant.conversations;

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
