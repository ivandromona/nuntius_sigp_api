package ao.adnlogico.nuntius.tenant.controller;

import ao.adnlogico.nuntius.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.tenant.model_assembler.ModuleModelAssembler;
import ao.adnlogico.nuntius.tenant.entity.Module;
import ao.adnlogico.nuntius.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.security.RequestAuthorization;
import ao.adnlogico.nuntius.tenant.repository.ModuleRepository;
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
public class ModuleController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    moduleService service;
    private final ModuleRepository repository;
    private final ModuleModelAssembler assembler;

    public ModuleController(ModuleRepository repository, ModuleModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/module")
    public CollectionModel<EntityModel<Module>> all()
    {
        List<EntityModel<Module>> modules = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(modules, linkTo(methodOn(ModuleController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/module")
    public ResponseEntity<?> save(@RequestBody Module module)
    {
        EntityModel<Module> entityModel = assembler.toModel(repository.save(module));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/module/{id}")
    public EntityModel<Module> findById(@PathVariable Integer id)
    {
        Module module = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Module(), id));

        return assembler.toModel(module);
    }

    @RequestAuthorization
    @PutMapping("/module/{id}")
    public ResponseEntity<?> update(@RequestBody Module newmodule, @PathVariable Integer id)
    {
        Module updatedmodule = repository.findById(id) //
                .map(module -> {
                    module.setName(newmodule.getName());
                    return repository.save(module);
                }) //
                .orElseGet(() -> {
                    newmodule.setId(id);
                    return repository.save(newmodule);
                });

        EntityModel<Module> entityModel = assembler.toModel(updatedmodule);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/module/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
