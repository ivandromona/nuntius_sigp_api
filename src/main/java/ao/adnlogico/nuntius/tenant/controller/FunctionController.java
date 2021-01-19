package ao.adnlogico.nuntius.tenant.controller;

import ao.adnlogico.nuntius.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.tenant.model_assembler.FunctionModelAssembler;
import ao.adnlogico.nuntius.tenant.repository.FunctionRepository;
import ao.adnlogico.nuntius.tenant.entity.Function;
import ao.adnlogico.nuntius.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.security.RequestAuthorization;
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
public class FunctionController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    functionService service;
    private final FunctionRepository repository;
    private final FunctionModelAssembler assembler;

    public FunctionController(FunctionRepository repository, FunctionModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/function")
    public CollectionModel<EntityModel<Function>> all()
    {
        List<EntityModel<Function>> functions = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(functions, linkTo(methodOn(FunctionController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/function")
    public ResponseEntity<?> save(@RequestBody Function function)
    {
        EntityModel<Function> entityModel = assembler.toModel(repository.save(function));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/function/{id}")
    public EntityModel<Function> findById(@PathVariable Integer id)
    {
        Function function = repository.findById(id) //
            .orElseThrow(() -> new EntityNotFoundException(new Function(), id));

        return assembler.toModel(function);
    }

    @RequestAuthorization
    @PutMapping("/function/{id}")
    public ResponseEntity<?> update(@RequestBody Function newFunction, @PathVariable Integer id)
    {
        Function updatedFunction = repository.findById(id) //
            .map(function -> {
                function.setName(newFunction.getName());
                return repository.save(function);
            }) //
            .orElseGet(() -> {
                newFunction.setId(id);
                return repository.save(newFunction);
            });

        EntityModel<Function> entityModel = assembler.toModel(updatedFunction);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/function/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
