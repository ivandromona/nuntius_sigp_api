/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.model_assembler;

import ao.adnlogico.nuntius.multitenant.controller.CategoryController;
import ao.adnlogico.nuntius.multitenant.tenant.entity.User;
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
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>>
{

    @Override
    public EntityModel<User> toModel(User user)
    {

        return EntityModel.of(user, //
            linkTo(methodOn(CategoryController.class).findById(user.getId())).withSelfRel(),
            linkTo(methodOn(CategoryController.class).all()).withRel("roles"));
    }
}
