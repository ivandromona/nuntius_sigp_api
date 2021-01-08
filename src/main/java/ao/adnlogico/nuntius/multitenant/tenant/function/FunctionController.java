package ao.adnlogico.nuntius.multitenant.tenant.function;

import ao.adnlogico.nuntius.multitenant.tenant.department.*;
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
public class FunctionController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    departmentService service;
    private final FunctionRepository repository;
    private final FunctionModelAssembler assembler;

    public FunctionController(FunctionRepository repository, FunctionModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/department")
    public CollectionModel<EntityModel<Function>> all()
    {
        List<EntityModel<Function>> departments = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(departments, linkTo(methodOn(FunctionController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/department")
    public ResponseEntity<?> save(@RequestBody Function department)
    {
        EntityModel<Function> entityModel = assembler.toModel(repository.save(department));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/department/{id}")
    public EntityModel<Function> findById(@PathVariable Integer id)
    {
        Function department = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Function(), id));

        return assembler.toModel(department);
    }

    @RequestAuthorization
    @PutMapping("/department/{id}")
    public ResponseEntity<?> update(@RequestBody Function newdepartment, @PathVariable Integer id)
    {
        Function updateddepartment = repository.findById(id) //
                .map(department -> {
                    department.setName(newdepartment.getName());
                    return repository.save(department);
                }) //
                .orElseGet(() -> {
                    newdepartment.setId(id);
                    return repository.save(newdepartment);
                });

        EntityModel<Function> entityModel = assembler.toModel(updateddepartment);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/department/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
