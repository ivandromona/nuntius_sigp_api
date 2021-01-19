package ao.adnlogico.nuntius.tenant.model_assembler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
import ao.adnlogico.nuntius.tenant.controller.EntityController;
import ao.adnlogico.nuntius.tenant.entity.Entities;
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
public class EntityModelAssembler implements RepresentationModelAssembler<Entities, EntityModel<Entities>>
{

    @Override
    public EntityModel<Entities> toModel(Entities process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(EntityController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(EntityController.class).all()).withRel("provinces"));
    }
}
