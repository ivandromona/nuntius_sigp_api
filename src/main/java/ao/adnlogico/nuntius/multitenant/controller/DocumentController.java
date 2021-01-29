package ao.adnlogico.nuntius.multitenant.controller;

import ao.adnlogico.nuntius.multitenant.tenant.document.Document;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import ao.adnlogico.nuntius.multitenant.tenant.model_assembler.DocumentModelAssembler;
import ao.adnlogico.nuntius.multitenant.tenant.repository.DocumentRepository;
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
public class DocumentController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    documentService service;
    private final DocumentRepository repository;
    private final DocumentModelAssembler assembler;

    public DocumentController(DocumentRepository repository, DocumentModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/document")
    public CollectionModel<EntityModel<Document>> all()
    {
        List<EntityModel<Document>> documents = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(documents, linkTo(methodOn(DocumentController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/document")
    public ResponseEntity<?> save(@RequestBody Document document)
    {
        EntityModel<Document> entityModel = assembler.toModel(repository.save(document));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/document/{id}")
    public EntityModel<Document> findById(@PathVariable Long id)
    {
        Document document = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Document(), id));

        return assembler.toModel(document);
    }

    @RequestAuthorization
    @PutMapping("/document/{id}")
    public ResponseEntity<?> update(@RequestBody Document newdocument, @PathVariable Long id)
    {
        Document updateddocument = repository.findById(id) //
                .map(document -> {
                    document.setContent(newdocument.getContent());
                    return repository.save(document);
                }) //
                .orElseGet(() -> {
                    newdocument.setId(id);
                    return repository.save(newdocument);
                });

        EntityModel<Document> entityModel = assembler.toModel(updateddocument);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/document/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
