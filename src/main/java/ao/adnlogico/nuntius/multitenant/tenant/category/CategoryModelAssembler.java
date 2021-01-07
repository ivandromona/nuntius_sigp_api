/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.category;

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
public class CategoryModelAssembler implements RepresentationModelAssembler<Category, EntityModel<Category>>
{

    @Override
    public EntityModel<Category> toModel(Category process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(CategoryController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(CategoryController.class).all()).withRel("provinces"));
    }
}
