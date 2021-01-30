/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.role;

import ao.adnlogico.nuntius.multitenant.tenant.role.RoleController;
import ao.adnlogico.nuntius.multitenant.tenant.role.Role;
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
public class RoleModelAssembler implements RepresentationModelAssembler<Role, EntityModel<Role>>
{

    @Override
    public EntityModel<Role> toModel(Role process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(RoleController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(RoleController.class).all()).withRel("provinces"));
    }
}
