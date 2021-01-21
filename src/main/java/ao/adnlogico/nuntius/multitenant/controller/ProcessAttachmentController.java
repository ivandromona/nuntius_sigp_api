package ao.adnlogico.nuntius.multitenant.controller;

import ao.adnlogico.nuntius.multitenant.tenant.entity.ProcessAttachment;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import ao.adnlogico.nuntius.multitenant.tenant.model_assembler.ProcessAttachmentModelAssembler;
import ao.adnlogico.nuntius.multitenant.tenant.repository.ProcessAttachmentRepository;
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
public class ProcessAttachmentController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    processAttachmentService service;
    private final ProcessAttachmentRepository repository;
    private final ProcessAttachmentModelAssembler assembler;

    public ProcessAttachmentController(ProcessAttachmentRepository repository, ProcessAttachmentModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/processAttachment")
    public CollectionModel<EntityModel<ProcessAttachment>> all()
    {
        List<EntityModel<ProcessAttachment>> processAttachments = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(processAttachments, linkTo(methodOn(ProcessAttachmentController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/processAttachment")
    public ResponseEntity<?> save(@RequestBody ProcessAttachment processAttachment)
    {
        EntityModel<ProcessAttachment> entityModel = assembler.toModel(repository.save(processAttachment));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/processAttachment/{id}")
    public EntityModel<ProcessAttachment> findById(@PathVariable Long id)
    {
        ProcessAttachment processAttachment = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new ProcessAttachment(), id));

        return assembler.toModel(processAttachment);
    }

    @RequestAuthorization
    @PutMapping("/processAttachment/{id}")
    public ResponseEntity<?> update(@RequestBody ProcessAttachment newprocessAttachment, @PathVariable Long id)
    {
        ProcessAttachment updatedprocessAttachment = repository.findById(id) //
                .map(processAttachment -> {
                    processAttachment.setDescription(newprocessAttachment.getDescription());
                    return repository.save(processAttachment);
                }) //
                .orElseGet(() -> {
                    newprocessAttachment.setId(id);
                    return repository.save(newprocessAttachment);
                });

        EntityModel<ProcessAttachment> entityModel = assembler.toModel(updatedprocessAttachment);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/processAttachment/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
