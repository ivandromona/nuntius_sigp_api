package ao.adnlogico.nuntius.multitenant.tenant.user.notification;

import ao.adnlogico.nuntius.multitenant.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Md. Amran Hossain | amrancse930@gmail.com
 */
@RestController
@RequestMapping("/nuntius/v1/api")
public class UserNotificationController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final UserNotificationRepository repository;
    private final UserNotificationModelAssembler assembler;

    public UserNotificationController(UserNotificationRepository repository, UserNotificationModelAssembler assembler)
    {
        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/userNotification/{id}")
    public EntityModel<UserNotification> findById(@PathVariable Long id)
    {
        UserNotification notification = repository.findById(id) //
            .orElseThrow(() -> new EntityNotFoundException(new UserNotification(), id));

        return assembler.toModel(notification);
    }

    @RequestAuthorization
    @PutMapping("/userNotification/{id}")
    public ResponseEntity<?> update(@RequestBody UserNotification newUserNotification, @PathVariable Long id)
    {
        UserNotification updatednotification = repository.findById(id) //
            .map(notification -> {
                notification.setIsReaded(newUserNotification.getIsReaded());
                return repository.save(notification);
            }) //
            .orElseGet(() -> {
                newUserNotification.setId(id);
                return repository.save(newUserNotification);
            });

        EntityModel<UserNotification> entityModel = assembler.toModel(updatednotification);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/userNotification/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
