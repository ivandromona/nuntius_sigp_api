/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.multitenant.nuntius.tenant.doctemplate;

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
public class DocTemplateModelAssembler implements RepresentationModelAssembler<DocTemplate, EntityModel<DocTemplate>>
{

    @Override
    public EntityModel<DocTemplate> toModel(DocTemplate process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(DocTemplateController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(DocTemplateController.class).all()).withRel("provinces"));
    }
}
