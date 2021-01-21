package ao.adnlogico.nuntius.multitenant.controller;

import ao.adnlogico.nuntius.multitenant.tenant.model_assembler.DocTemplateModelAssembler;
import ao.adnlogico.nuntius.multitenant.tenant.repository.DocTemplateRepository;
import ao.adnlogico.nuntius.multitenant.tenant.entity.DocTemplate;
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
public class DocTemplateController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    doctemplateService service;
    private final DocTemplateRepository repository;
    private final DocTemplateModelAssembler assembler;

    public DocTemplateController(DocTemplateRepository repository, DocTemplateModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/doctemplate")
    public CollectionModel<EntityModel<DocTemplate>> all()
    {
        List<EntityModel<DocTemplate>> doctemplates = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(doctemplates, linkTo(methodOn(DocTemplateController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/doctemplate")
    public ResponseEntity<?> save(@RequestBody DocTemplate doctemplate)
    {
        EntityModel<DocTemplate> entityModel = assembler.toModel(repository.save(doctemplate));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/doctemplate/{id}")
    public EntityModel<DocTemplate> findById(@PathVariable Long id)
    {
        DocTemplate doctemplate = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new DocTemplate(), id));

        return assembler.toModel(doctemplate);
    }

    @RequestAuthorization
    @PutMapping("/doctemplate/{id}")
    public ResponseEntity<?> update(@RequestBody DocTemplate newDocTemplate, @PathVariable Long id)
    {
        DocTemplate updatedDocTemplate = repository.findById(id) //
                .map(docTemplate -> {
                    docTemplate.setName(newDocTemplate.getName());
                    return repository.save(docTemplate);
                }) //
                .orElseGet(() -> {
                    newDocTemplate.setId(id);
                    return repository.save(newDocTemplate);
                });

        EntityModel<DocTemplate> entityModel = assembler.toModel(updatedDocTemplate);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/doctemplate/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
