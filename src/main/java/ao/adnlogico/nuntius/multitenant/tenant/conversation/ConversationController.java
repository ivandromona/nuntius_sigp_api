package ao.adnlogico.nuntius.multitenant.tenant.conversation;

import ao.adnlogico.nuntius.multitenant.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import ao.adnlogico.nuntius.multitenant.tenant.conversation.Conversation;
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
public class ConversationController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    conversationService service;
    private final ConversationRepository repository;
    private final ConversationModelAssembler assembler;

    public ConversationController(ConversationRepository repository, ConversationModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/conversation")
    public CollectionModel<EntityModel<Conversation>> all()
    {
        List<EntityModel<Conversation>> conversations = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(conversations, linkTo(methodOn(ConversationController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/conversation")
    public ResponseEntity<?> save(@RequestBody Conversation conversation)
    {
        EntityModel<Conversation> entityModel = assembler.toModel(repository.save(conversation));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/conversation/{id}")
    public EntityModel<Conversation> findById(@PathVariable Long id)
    {
        Conversation conversation = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Conversation(), id));

        return assembler.toModel(conversation);
    }

    @RequestAuthorization
    @PutMapping("/conversation/{id}")
    public ResponseEntity<?> update(@RequestBody Conversation newconversation, @PathVariable Long id)
    {
        Conversation updatedconversation = repository.findById(id) //
                .map(conversation -> {
                    conversation.setChatKey(newconversation.getChatKey());
                    return repository.save(conversation);
                }) //
                .orElseGet(() -> {
                    newconversation.setId(id);
                    return repository.save(newconversation);
                });

        EntityModel<Conversation> entityModel = assembler.toModel(updatedconversation);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/conversation/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
