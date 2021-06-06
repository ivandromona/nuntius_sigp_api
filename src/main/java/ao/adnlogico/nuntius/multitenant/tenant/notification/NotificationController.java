package ao.adnlogico.nuntius.multitenant.tenant.notification;

import ao.adnlogico.nuntius.multitenant.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import ao.adnlogico.nuntius.multitenant.tenant.notification.Notification;
import ao.adnlogico.nuntius.multitenant.tenant.user.User;
import ao.adnlogico.nuntius.multitenant.tenant.user.UserRepository;
import ao.adnlogico.nuntius.multitenant.util.search.CustomRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserRepository userRepository;
    private final NotificationRepository repository;
    private final NotificationModelAssembler assembler;

    public NotificationController(UserRepository userRepository, NotificationRepository repository, NotificationModelAssembler assembler)
    {

        this.userRepository = userRepository;
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
    @GetMapping("/notification/current")
    public CollectionModel<EntityModel<Notification>> allByUser()
    {
//      Implementar perquisa generica com subníveis
        String search = "users.id==" + loadCurrentLogedUser().getId();;
        Node rootNode = new RSQLParser().parse(search);
        Specification<Notification> spec = rootNode.accept(new CustomRsqlVisitor<>());

        List<EntityModel<Notification>> notifications = repository.findAll(spec).stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(notifications, linkTo(methodOn(NotificationController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/notification")
    public ResponseEntity<?> save(@RequestBody Notification notification)
    {
        notification.setCreatedAt(Calendar.getInstance().getTime());
        EntityModel<Notification> entityModel = assembler.toModel(repository.save(notification));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/notification/{id}")
    public EntityModel<Notification> findById(@PathVariable Long id)
    {
        Notification notification = repository.findById(id) //
            .orElseThrow(() -> new EntityNotFoundException(new Notification(), id));

        return assembler.toModel(notification);
    }

    @RequestAuthorization
    @PutMapping("/notification/{id}")
    public ResponseEntity<?> update(@RequestBody Notification newNotification, @PathVariable Long id)
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
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    private User loadCurrentLogedUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails local = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByEmail(local.getUsername());
        return user;
    }

}
