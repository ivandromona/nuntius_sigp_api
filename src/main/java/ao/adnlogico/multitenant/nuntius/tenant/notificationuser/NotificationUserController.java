package ao.adnlogico.multitenant.nuntius.tenant.notificationuser;

import ao.adnlogico.multitenant.nuntius.controller.AuthenticationController;
import ao.adnlogico.multitenant.nuntius.exception.EntityNotFoundException;
import ao.adnlogico.multitenant.nuntius.security.RequestAuthorization;
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
public class NotificationUserController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    notificationUserService service;
    private final NotificationUserRepository repository;
    private final NotificationUserModelAssembler assembler;

    public NotificationUserController(NotificationUserRepository repository, NotificationUserModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/notificationUser")
    public CollectionModel<EntityModel<NotificationUser>> all()
    {
        List<EntityModel<NotificationUser>> notificationUsers = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(notificationUsers, linkTo(methodOn(NotificationUserController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/notificationUser")
    public ResponseEntity<?> save(@RequestBody NotificationUser notificationUser)
    {
        EntityModel<NotificationUser> entityModel = assembler.toModel(repository.save(notificationUser));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/notificationUser/{id}")
    public EntityModel<NotificationUser> findById(@PathVariable Integer id)
    {
        NotificationUser notificationUser = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new NotificationUser(), id));

        return assembler.toModel(notificationUser);
    }

    @RequestAuthorization
    @PutMapping("/notificationUser/{id}")
    public ResponseEntity<?> update(@RequestBody NotificationUser newnotificationUser, @PathVariable Integer id)
    {
        NotificationUser updatednotificationUser = repository.findById(id) //
                .map(notificationUser -> {
                    notificationUser.setUsers(newnotificationUser.getUsers());
                    return repository.save(notificationUser);
                }) //
                .orElseGet(() -> {
                    newnotificationUser.setIsDismiss(false);
                    return repository.save(newnotificationUser);
                });

        EntityModel<NotificationUser> entityModel = assembler.toModel(updatednotificationUser);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/notificationUser/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
