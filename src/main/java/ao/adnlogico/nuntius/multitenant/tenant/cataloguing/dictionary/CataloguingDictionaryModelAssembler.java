/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.dictionary;

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
public class CataloguingDictionaryModelAssembler implements RepresentationModelAssembler<CataloguingDictionary, EntityModel<CataloguingDictionary>>
{

    @Override
    public EntityModel<CataloguingDictionary> toModel(CataloguingDictionary cataloguingDictionary)
    {

        return EntityModel.of(cataloguingDictionary, //
            linkTo(methodOn(CataloguingDictionaryController.class).findById(cataloguingDictionary.getId())).withSelfRel(),
            linkTo(methodOn(CataloguingDictionaryController.class).all()).withRel("provinces"));
    }
}
