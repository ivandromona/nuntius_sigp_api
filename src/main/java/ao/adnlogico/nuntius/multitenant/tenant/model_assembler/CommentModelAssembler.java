/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.model_assembler;

import ao.adnlogico.nuntius.multitenant.tenant.comment.Comment;
import ao.adnlogico.nuntius.multitenant.controller.CommentController;
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
public class CommentModelAssembler implements RepresentationModelAssembler<Comment, EntityModel<Comment>>
{

    @Override
    public EntityModel<Comment> toModel(Comment process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(CommentController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(CommentController.class).all()).withRel("provinces"));
    }
}
