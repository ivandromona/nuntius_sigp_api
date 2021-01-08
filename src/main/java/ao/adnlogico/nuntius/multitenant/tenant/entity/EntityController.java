package ao.adnlogico.nuntius.multitenant.tenant.entity;

import ao.adnlogico.nuntius.multitenant.controller.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
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
public class EntityController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    entitiesService service;
    private final EntityRepository repository;
    private final EntityModelAssembler assembler;

    public EntityController(EntityRepository repository, EntityModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/entities")
    public CollectionModel<EntityModel<Entities>> all()
    {
        List<EntityModel<Entities>> entities = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(entities, linkTo(methodOn(EntityController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/entities")
    public ResponseEntity<?> save(@RequestBody Entities entities)
    {
        EntityModel<Entities> entityModel = assembler.toModel(repository.save(entities));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/entities/{id}")
    public EntityModel<Entities> findById(@PathVariable Integer id)
    {
        Entities entities = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Entities(), id));

        return assembler.toModel(entities);
    }

    @RequestAuthorization
    @PutMapping("/entities/{id}")
    public ResponseEntity<?> update(@RequestBody Entities newentities, @PathVariable Integer id)
    {
        Entities updatedentities = repository.findById(id) //
                .map(entities -> {
                    entities.setName(newentities.getName());
                    return repository.save(entities);
                }) //
                .orElseGet(() -> {
                    newentities.setId(id);
                    return repository.save(newentities);
                });

        EntityModel<Entities> entityModel = assembler.toModel(updatedentities);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/entities/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
