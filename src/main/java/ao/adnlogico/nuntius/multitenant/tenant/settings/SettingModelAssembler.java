/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.settings;

import ao.adnlogico.nuntius.multitenant.tenant.settings.SettingController;
import ao.adnlogico.nuntius.multitenant.tenant.settings.Setting;
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
public class SettingModelAssembler implements RepresentationModelAssembler<Setting, EntityModel<Setting>>
{

    @Override
    public EntityModel<Setting> toModel(Setting process)
    {

        return EntityModel.of(process, //
                linkTo(methodOn(SettingController.class).findById(process.getId())).withSelfRel(),
                linkTo(methodOn(SettingController.class).all()).withRel("provinces"));
    }
}
