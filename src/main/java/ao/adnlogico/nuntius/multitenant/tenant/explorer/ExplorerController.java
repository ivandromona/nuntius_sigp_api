package ao.adnlogico.nuntius.multitenant.tenant.explorer;

import ao.adnlogico.nuntius.multitenant.tenant.user.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.tenant.explorer.Explorer;
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
public class ExplorerController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    explorerService service;
    private final ExplorerRepository repository;
    private final ExplorerModelAssembler assembler;

    public ExplorerController(ExplorerRepository repository, ExplorerModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/explorer")
    public CollectionModel<EntityModel<Explorer>> all()
    {
        List<EntityModel<Explorer>> explorers = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(explorers, linkTo(methodOn(ExplorerController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/explorer")
    public ResponseEntity<?> save(@RequestBody Explorer explorer)
    {
        EntityModel<Explorer> entityModel = assembler.toModel(repository.save(explorer));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/explorer/{id}")
    public EntityModel<Explorer> findById(@PathVariable Long id)
    {
        Explorer explorer = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Explorer(), id));

        return assembler.toModel(explorer);
    }

    @RequestAuthorization
    @PutMapping("/explorer/{id}")
    public ResponseEntity<?> update(@RequestBody Explorer newexplorer, @PathVariable Long id)
    {
        Explorer updatedexplorer = repository.findById(id) //
                .map(explorer -> {
                    explorer.setSubject(newexplorer.getSubject());
                    return repository.save(explorer);
                }) //
                .orElseGet(() -> {
                    newexplorer.setId(id);
                    return repository.save(newexplorer);
                });

        EntityModel<Explorer> entityModel = assembler.toModel(updatedexplorer);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/explorer/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
