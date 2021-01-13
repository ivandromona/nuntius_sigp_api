/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.multitenant.nuntius.tenant.notificationuser;

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
public class NotificationUserModelAssembler implements RepresentationModelAssembler<NotificationUser, EntityModel<NotificationUser>>
{

    @Override
    public EntityModel<NotificationUser> toModel(NotificationUser process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(NotificationUserController.class).findById(process.getNotificationUsersPK().hashCode())).withSelfRel(),
                linkTo(methodOn(NotificationUserController.class).all()).withRel("provinces"));
    }
}
