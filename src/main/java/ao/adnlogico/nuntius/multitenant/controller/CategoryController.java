package ao.adnlogico.nuntius.multitenant.controller;

import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import ao.adnlogico.nuntius.multitenant.tenant.entity.Category;
import ao.adnlogico.nuntius.multitenant.tenant.model_assembler.CategoryModelAssembler;
import ao.adnlogico.nuntius.multitenant.tenant.repository.CategoryRepository;
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
public class CategoryController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    CategoryService service;
    private final CategoryRepository repository;
    private final CategoryModelAssembler assembler;

    public CategoryController(CategoryRepository repository, CategoryModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/category")
    public CollectionModel<EntityModel<Category>> all()
    {
        List<EntityModel<Category>> categorys = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(categorys, linkTo(methodOn(CategoryController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/category")
    public ResponseEntity<?> save(@RequestBody Category category)
    {
        EntityModel<Category> entityModel = assembler.toModel(repository.save(category));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/category/{id}")
    public EntityModel<Category> findById(@PathVariable Long id)
    {
        Category category = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Category(), id));

        return assembler.toModel(category);
    }

    @RequestAuthorization
    @PutMapping("/category/{id}")
    public ResponseEntity<?> update(@RequestBody Category newCategory, @PathVariable Long id)
    {
        Category updatedCategory = repository.findById(id) //
                .map(category -> {
                    category.setName(newCategory.getName());
                    return repository.save(category);
                }) //
                .orElseGet(() -> {
                    newCategory.setId(id);
                    return repository.save(newCategory);
                });

        EntityModel<Category> entityModel = assembler.toModel(updatedCategory);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
