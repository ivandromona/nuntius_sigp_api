package ao.adnlogico.nuntius.multitenant.tenant.notification;

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
public class NotificationController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    notificationService service;
    private final NotificationRepository repository;
    private final NotificationModelAssembler assembler;

    public NotificationController(NotificationRepository repository, NotificationModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/notification")
    public CollectionModel<EntityModel<Notification>> all()
    {
        List<EntityModel<Notification>> notifications = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(notifications, linkTo(methodOn(NotificationController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/notification")
    public ResponseEntity<?> save(@RequestBody Notification notification)
    {
        EntityModel<Notification> entityModel = assembler.toModel(repository.save(notification));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/notification/{id}")
    public EntityModel<Notification> findById(@PathVariable Integer id)
    {
        Notification notification = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Notification(), id));

        return assembler.toModel(notification);
    }

    @RequestAuthorization
    @PutMapping("/notification/{id}")
    public ResponseEntity<?> update(@RequestBody Notification newNotification, @PathVariable Integer id)
    {
        Notification updatednotification = repository.findById(id) //
                .map(notification -> {
                    notification.setContent(newNotification.getContent());
                    return repository.save(notification);
                }) //
                .orElseGet(() -> {
                    newNotification.setId(id);
                    return repository.save(newNotification);
                });

        EntityModel<Notification> entityModel = assembler.toModel(updatednotification);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/notification/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
