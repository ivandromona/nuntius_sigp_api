package ao.adnlogico.nuntius.multitenant.controller;

import ao.adnlogico.nuntius.multitenant.tenant.entity.Progress;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import ao.adnlogico.nuntius.multitenant.tenant.model_assembler.ProgressModelAssembler;
import ao.adnlogico.nuntius.multitenant.tenant.repository.ProgressRepository;
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
public class ProgressController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    ProgressService service;
    private final ProgressRepository repository;
    private final ProgressModelAssembler assembler;

    public ProgressController(ProgressRepository repository, ProgressModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/progress")
    public CollectionModel<EntityModel<Progress>> all()
    {
        List<EntityModel<Progress>> progress = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(progress, linkTo(methodOn(ProgressController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/progress")
    public ResponseEntity<?> save(@RequestBody Progress progress)
    {
        EntityModel<Progress> entityModel = assembler.toModel(repository.save(progress));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/progress/{id}")
    public EntityModel<Progress> findById(@PathVariable Long id)
    {
        Progress progress = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Progress(), id));

        return assembler.toModel(progress);
    }

    @RequestAuthorization
    @PutMapping("/progress/{id}")
    public ResponseEntity<?> update(@RequestBody Progress newprogress, @PathVariable Long id)
    {
        Progress updatedprogress = repository.findById(id) //
                .map(progress -> {
                    progress.setName(newprogress.getName());
                    return repository.save(progress);
                }) //
                .orElseGet(() -> {
                    newprogress.setId(id);
                    return repository.save(newprogress);
                });

        EntityModel<Progress> entityModel = assembler.toModel(updatedprogress);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/progress/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
