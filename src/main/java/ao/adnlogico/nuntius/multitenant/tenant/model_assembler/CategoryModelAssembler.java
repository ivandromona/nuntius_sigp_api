/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.model_assembler;

import ao.adnlogico.nuntius.multitenant.tenant.entity.Category;
import ao.adnlogico.nuntius.multitenant.controller.CategoryController;
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
    public EntityModel<Category> toModel(Category category)
    {

        return EntityModel.of(category, //
                linkTo(methodOn(CategoryController.class).findById(category.getId())).withSelfRel(),
                linkTo(methodOn(CategoryController.class).all()).withRel("provinces"));
    }
}
