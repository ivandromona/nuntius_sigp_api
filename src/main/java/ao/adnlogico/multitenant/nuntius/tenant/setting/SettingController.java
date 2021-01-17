package ao.adnlogico.multitenant.nuntius.tenant.setting;

import ao.adnlogico.multitenant.nuntius.tenant.user.AuthenticationController;
import ao.adnlogico.multitenant.nuntius.exception.EntityNotFoundException;
import ao.adnlogico.multitenant.nuntius.security.RequestAuthorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Md. Amran Hossain | amrancse930@gmail.com
 */
@RestController
@RequestMapping("/nuntius/v1/api")
public class SettingController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    settingService service;
    private final SettingRepository repository;
    private final SettingModelAssembler assembler;

    public SettingController(SettingRepository repository, SettingModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/setting")
    public CollectionModel<EntityModel<Setting>> all()
    {
        List<EntityModel<Setting>> settings = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(settings, linkTo(methodOn(SettingController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/setting")
    public ResponseEntity<?> save(@RequestBody Setting setting)
    {
        EntityModel<Setting> entityModel = assembler.toModel(repository.save(setting));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/setting/{id}")
    public EntityModel<Setting> findById(@PathVariable Integer id)
    {
        Setting setting = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Setting(), id));

        return assembler.toModel(setting);
    }

    @RequestAuthorization
    @PutMapping("/setting/{id}")
    public ResponseEntity<?> update(@RequestBody Setting newsetting, @PathVariable Integer id)
    {
        Setting updatedsetting = repository.findById(id) //
                .map(setting -> {
                    setting.setKey(newsetting.getKey());
                    return repository.save(setting);
                }) //
                .orElseGet(() -> {
                    newsetting.setId(id);
                    return repository.save(newsetting);
                });

        EntityModel<Setting> entityModel = assembler.toModel(updatedsetting);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/setting/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
