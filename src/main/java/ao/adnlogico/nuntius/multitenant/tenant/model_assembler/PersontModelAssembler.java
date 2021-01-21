/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.model_assembler;

import ao.adnlogico.nuntius.multitenant.controller.PersonController;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Person;
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
public class PersontModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>>
{

    @Override
    public EntityModel<Person> toModel(Person process)
    {

        return EntityModel.of(process, //
            linkTo(methodOn(PersonController.class).findById(process.getId())).withSelfRel(),
            linkTo(methodOn(PersonController.class).all()).withRel("provinces"));
    }
}
