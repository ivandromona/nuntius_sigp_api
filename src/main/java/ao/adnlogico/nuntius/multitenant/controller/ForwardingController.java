package ao.adnlogico.nuntius.multitenant.controller;

import ao.adnlogico.nuntius.multitenant.tenant.repository.ForwardingRepository;
import ao.adnlogico.nuntius.multitenant.tenant.forwarding.Forwarding;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import ao.adnlogico.nuntius.multitenant.tenant.model_assembler.ForwardingModelAssembler;
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
public class ForwardingController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    forwardingService service;
    private final ForwardingRepository repository;
    private final ForwardingModelAssembler assembler;

    public ForwardingController(ForwardingRepository repository, ForwardingModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/forwarding")
    public CollectionModel<EntityModel<Forwarding>> all()
    {
        List<EntityModel<Forwarding>> forwardings = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(forwardings, linkTo(methodOn(ForwardingController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/forwarding")
    public ResponseEntity<?> save(@RequestBody Forwarding forwarding)
    {
        EntityModel<Forwarding> entityModel = assembler.toModel(repository.save(forwarding));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/forwarding/{id}")
    public EntityModel<Forwarding> findById(@PathVariable Long id)
    {
        Forwarding forwarding = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Forwarding(), id));

        return assembler.toModel(forwarding);
    }

    @RequestAuthorization
    @PutMapping("/forwarding/{id}")
    public ResponseEntity<?> update(@RequestBody Forwarding newforwarding, @PathVariable Long id)
    {
        Forwarding updatedforwarding = repository.findById(id) //
                .map(forwarding -> {
                    forwarding.setAction(newforwarding.getAction());
                    return repository.save(forwarding);
                }) //
                .orElseGet(() -> {
                    newforwarding.setId(id);
                    return repository.save(newforwarding);
                });

        EntityModel<Forwarding> entityModel = assembler.toModel(updatedforwarding);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/forwarding/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
