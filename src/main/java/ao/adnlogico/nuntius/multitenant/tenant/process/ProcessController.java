package ao.adnlogico.nuntius.multitenant.tenant.process;

import ao.adnlogico.nuntius.multitenant.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
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
public class ProcessController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    ProcessService service;
    private final ProcessRepository repository;
    private final ProcessModelAssembler assembler;

    public ProcessController(ProcessRepository repository, ProcessModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

//    @RequestAuthorization
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public ResponseEntity<Object> getAllProcess()
//    {
//        LOGGER.info("getAllProduct() method call...");
//        return new ResponseEntity<>(service.getAllProcess(), HttpStatus.OK);
//    }
    @RequestAuthorization
    @GetMapping("/process")
    public CollectionModel<EntityModel<Process>> all()
    {
        List<EntityModel<Process>> processs = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(processs, linkTo(methodOn(ProcessController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/process")
    public ResponseEntity<?> save(@RequestBody Process process)
    {
        EntityModel<Process> entityModel = assembler.toModel(repository.save(process));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/process/{id}")
    public EntityModel<Process> findById(@PathVariable Long id)
    {
        Process process = repository.findById(id) //
            .orElseThrow(() -> new EntityNotFoundException(new Process(), id));

        return assembler.toModel(process);
    }

    @RequestAuthorization
    @PutMapping("/process/{id}")
    public ResponseEntity<?> update(@RequestBody Process newProcess, @PathVariable Long id)
    {
        Process updatedProcess = repository.findById(id) //
            .map(process -> {
                process.setOrigin(newProcess.getOrigin());
                process.setOriginDate(newProcess.getOriginDate());
                process.setDeadline(newProcess.getDeadline());
//                process.setProcessNumber(newProcess.getProcessNumber());
                process.setExternalReference(newProcess.getExternalReference());
                process.setSubject(newProcess.getSubject());
                process.setDescription(newProcess.getDescription());
                process.setConfidential(newProcess.getConfidential());
                process.setStatus(newProcess.getStatus());
                process.setFiled(newProcess.getFiled());
//                process.setCreatedAt(newProcess.getCreatedAt());
                process.setUpdatedAt(newProcess.getUpdatedAt());
                process.setProcessType(newProcess.getProcessType());
                process.setExisting(newProcess.getExisting());
                process.setClaimant(newProcess.getClaimant());
                process.setFkRoleType(newProcess.getFkRoleType());
                process.setFkClaimantEntity(newProcess.getFkClaimantEntity());
                process.setFkApproval(newProcess.getFkApproval());
                process.setFkOperatorUser(newProcess.getFkOperatorUser());
                process.setFkResponsibleUser(newProcess.getFkResponsibleUser());
                process.setFkClaimantPerson(newProcess.getFkClaimantPerson());
                process.setFkCategory(newProcess.getFkCategory());
                process.setFkExplorer(newProcess.getFkExplorer());
                return repository.save(process);
            }) //
            .orElseGet(() -> {
                newProcess.setId(id);
                return repository.save(newProcess);
            });

        EntityModel<Process> entityModel = assembler.toModel(updatedProcess);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/process/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
