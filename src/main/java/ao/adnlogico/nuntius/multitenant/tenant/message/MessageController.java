package ao.adnlogico.nuntius.multitenant.tenant.message;

import ao.adnlogico.nuntius.multitenant.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.tenant.message.Message;
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
public class MessageController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    messageService service;
    private final MessageRepository repository;
    private final MessageModelAssembler assembler;

    public MessageController(MessageRepository repository, MessageModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/message")
    public CollectionModel<EntityModel<Message>> all()
    {
        List<EntityModel<Message>> messages = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(messages, linkTo(methodOn(MessageController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/message")
    public ResponseEntity<?> save(@RequestBody Message message)
    {
        EntityModel<Message> entityModel = assembler.toModel(repository.save(message));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/message/{id}")
    public EntityModel<Message> findById(@PathVariable Long id)
    {
        Message message = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Message(), id));

        return assembler.toModel(message);
    }

    @RequestAuthorization
    @PutMapping("/message/{id}")
    public ResponseEntity<?> update(@RequestBody Message newmessage, @PathVariable Long id)
    {
        Message updatedmessage = repository.findById(id) //
                .map(message -> {
                    message.setContent(newmessage.getContent());
                    return repository.save(message);
                }) //
                .orElseGet(() -> {
                    newmessage.setId(id);
                    return repository.save(newmessage);
                });

        EntityModel<Message> entityModel = assembler.toModel(updatedmessage);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/message/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
