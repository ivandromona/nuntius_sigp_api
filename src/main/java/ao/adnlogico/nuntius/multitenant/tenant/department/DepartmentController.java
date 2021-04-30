package ao.adnlogico.nuntius.multitenant.tenant.department;

import ao.adnlogico.nuntius.multitenant.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.tenant.department.Department;
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
public class DepartmentController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    departmentService service;
    private final DepartmentRepository repository;
    private final DepartmentModelAssembler assembler;

    public DepartmentController(DepartmentRepository repository, DepartmentModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/department")
    public CollectionModel<EntityModel<Department>> all()
    {
        List<EntityModel<Department>> departments = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(departments, linkTo(methodOn(DepartmentController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/department")
    public ResponseEntity<?> save(@RequestBody Department department)
    {
        EntityModel<Department> entityModel = assembler.toModel(repository.save(department));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/department/{id}")
    public EntityModel<Department> findById(@PathVariable Long id)
    {
        Department department = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Department(), id));

        return assembler.toModel(department);
    }

    @RequestAuthorization
    @PutMapping("/department/{id}")
    public ResponseEntity<?> update(@RequestBody Department newdepartment, @PathVariable Long id)
    {
        Department updateddepartment = repository.findById(id) //
                .map(department -> {
                    department.setName(newdepartment.getName());
                    return repository.save(department);
                }) //
                .orElseGet(() -> {
                    newdepartment.setId(id);
                    return repository.save(newdepartment);
                });

        EntityModel<Department> entityModel = assembler.toModel(updateddepartment);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/department/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
