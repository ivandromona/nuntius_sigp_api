package ao.adnlogico.nuntius.multitenant.controller;

import ao.adnlogico.nuntius.multitenant.tenant.role.Role;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import ao.adnlogico.nuntius.multitenant.tenant.model_assembler.RoleModelAssembler;
import ao.adnlogico.nuntius.multitenant.tenant.repository.RoleRepository;
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
public class RoleController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    roleService service;
    private final RoleRepository repository;
    private final RoleModelAssembler assembler;

    public RoleController(RoleRepository repository, RoleModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/role")
    public CollectionModel<EntityModel<Role>> all()
    {
        List<EntityModel<Role>> roles = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(roles, linkTo(methodOn(RoleController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/role")
    public ResponseEntity<?> save(@RequestBody Role role)
    {
        EntityModel<Role> entityModel = assembler.toModel(repository.save(role));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/role/{id}")
    public EntityModel<Role> findById(@PathVariable Long id)
    {
        Role role = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Role(), id));

        return assembler.toModel(role);
    }

    @RequestAuthorization
    @PutMapping("/role/{id}")
    public ResponseEntity<?> update(@RequestBody Role newrole, @PathVariable Long id)
    {
        Role updatedrole = repository.findById(id) //
                .map(role -> {
                    role.setName(newrole.getName());
                    return repository.save(role);
                }) //
                .orElseGet(() -> {
                    newrole.setId(id);
                    return repository.save(newrole);
                });

        EntityModel<Role> entityModel = assembler.toModel(updatedrole);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
