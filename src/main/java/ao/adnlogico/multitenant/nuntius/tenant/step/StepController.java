package ao.adnlogico.multitenant.nuntius.tenant.step;

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
public class StepController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    stepService service;
    private final StepRepository repository;
    private final StepModelAssembler assembler;

    public StepController(StepRepository repository, StepModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/step")
    public CollectionModel<EntityModel<Step>> all()
    {
        List<EntityModel<Step>> steps = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(steps, linkTo(methodOn(StepController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/step")
    public ResponseEntity<?> save(@RequestBody Step step)
    {
        EntityModel<Step> entityModel = assembler.toModel(repository.save(step));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/step/{id}")
    public EntityModel<Step> findById(@PathVariable Integer id)
    {
        Step step = repository.findById(id) //
            .orElseThrow(() -> new EntityNotFoundException(new Step(), id));

        return assembler.toModel(step);
    }

    @RequestAuthorization
    @PutMapping("/step/{id}")
    public ResponseEntity<?> update(@RequestBody Step newstep, @PathVariable Integer id)
    {
        Step updatedstep = repository.findById(id) //
            .map(step -> {
                step.setName(newstep.getName());
                return repository.save(step);
            }) //
            .orElseGet(() -> {
                newstep.setId(id);
                return repository.save(newstep);
            });

        EntityModel<Step> entityModel = assembler.toModel(updatedstep);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/step/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
