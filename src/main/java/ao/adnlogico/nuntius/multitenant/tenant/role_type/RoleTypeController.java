package ao.adnlogico.nuntius.multitenant.tenant.role_type;

import ao.adnlogico.nuntius.multitenant.tenant.user.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.tenant.role_type.RoleType;
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
public class RoleTypeController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    roleTypeService service;
    private final RoleTypeRepository repository;
    private final RoleTypeModelAssembler assembler;

    public RoleTypeController(RoleTypeRepository repository, RoleTypeModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/roleType")
    public CollectionModel<EntityModel<RoleType>> all()
    {
        List<EntityModel<RoleType>> roleTypes = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(roleTypes, linkTo(methodOn(RoleTypeController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/roleType")
    public ResponseEntity<?> save(@RequestBody RoleType roleType)
    {
        EntityModel<RoleType> entityModel = assembler.toModel(repository.save(roleType));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/roleType/{id}")
    public EntityModel<RoleType> findById(@PathVariable Long id)
    {
        RoleType roleType = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new RoleType(), id));

        return assembler.toModel(roleType);
    }

    @RequestAuthorization
    @PutMapping("/roleType/{id}")
    public ResponseEntity<?> update(@RequestBody RoleType newroleType, @PathVariable Long id)
    {
        RoleType updatedroleType = repository.findById(id) //
                .map(roleType -> {
                    roleType.setName(newroleType.getName());
                    return repository.save(roleType);
                }) //
                .orElseGet(() -> {
                    newroleType.setId(id);
                    return repository.save(newroleType);
                });

        EntityModel<RoleType> entityModel = assembler.toModel(updatedroleType);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/roleType/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
