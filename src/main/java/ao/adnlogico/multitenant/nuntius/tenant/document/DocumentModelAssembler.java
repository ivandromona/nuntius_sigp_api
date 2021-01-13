/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.multitenant.nuntius.tenant.document;

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
public class DocumentModelAssembler implements RepresentationModelAssembler<Document, EntityModel<Document>>
{

    @Override
    public EntityModel<Document> toModel(Document process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(DocumentController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(DocumentController.class).all()).withRel("provinces"));
    }
}
