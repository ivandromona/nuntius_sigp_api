/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.user;

import ao.adnlogico.nuntius.multitenant.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.security.CustomPasswordEncoder;
import ao.adnlogico.nuntius.multitenant.dto.AuthResponse;
import ao.adnlogico.nuntius.multitenant.dto.UserPasswordUpdater;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import ao.adnlogico.nuntius.multitenant.util.ApiError;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author domingos.fernando
 */
@RestController
@RequestMapping("/nuntius/v1/api")
public class UserController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    roleTypeService service;
    private final UserRepository repository;
    private final CustomPasswordEncoder pwdEncoder;
    private final UserModelAssembler assembler;

    public UserController(UserRepository repository, CustomPasswordEncoder pwdEncoder, UserModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
        this.pwdEncoder = pwdEncoder;
    }

    @RequestAuthorization
    @RequestMapping(value = "/user/current", method = RequestMethod.GET)
    public ResponseEntity<?> userInfo(@RequestParam String userName) throws AuthenticationException
    {
        User current = repository.findByEmail(userName);
        AuthResponse response = new AuthResponse(current.getId(), current.getEmail(), "");
        return ResponseEntity.ok(response);
    }

    @RequestAuthorization
    @GetMapping("/user")
    public CollectionModel<EntityModel<User>> all()
    {
        List<EntityModel<User>> users = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/user")
    public ResponseEntity<?> save(@RequestBody User user)
    {

        user.setPassword(pwdEncoder.getPasswordEncoder().encode(user.getPassword()));
        EntityModel<User> entityModel = assembler.toModel(repository.save(user));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/user/{id}")
    public EntityModel<User> findById(@PathVariable Long id)
    {
        User user = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new User(), id));

        return assembler.toModel(user);
    }

    @RequestAuthorization
    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@RequestBody User newUser, @PathVariable Long id)
    {
        User updatedUser = repository.findById(id) //
                .map(user -> {
                    user.setPhone(newUser.getPhone());
                    user.setPhoneAlt(newUser.getPhoneAlt());
                    user.setMechanographicNumber(newUser.getMechanographicNumber());
                    user.setDescription(newUser.getDescription());
                    user.setUpdatedAt(newUser.getCreatedAt());
                    user.setFkFunction(newUser.getFkFunction());
                    user.setFkRole(newUser.getFkRole());
                    return repository.save(user);
                }) //
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });

        EntityModel<User> entityModel = assembler.toModel(updatedUser);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @PutMapping("/user/password/{id}")
    public ResponseEntity<?> updatePassword(@RequestBody UserPasswordUpdater newUserPasswd, @PathVariable Long id)
    {
        User user = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new User(), id));

        ApiError apiError;
        LOGGER.info("Old Encripted password: " + newUserPasswd.getOldPassword());
        LOGGER.info("Stored Encripted password: " + user.getPassword());
        if (pwdEncoder.getPasswordEncoder().matches(newUserPasswd.getOldPassword(), user.getPassword())) {
            user.setPassword(pwdEncoder.getPasswordEncoder().encode(newUserPasswd.getNewPassword()));
            repository.save(user);
            apiError = new ApiError(HttpStatus.OK, "A sua palavra passe foi atualizada com sucesso de hoje em diante passará a usar a nova palavra passe", "Operação efetuada com sucesso");
        }
        else {
            apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Erro ao tentar atualizar a password, confirme sua password anterior.", "Passwords diferentes.");
        }
        return ResponseEntity.ok(apiError);
    }

    @PutMapping("/user/resetPassword/{id}")
    public ResponseEntity<?> updatePassword(@RequestBody User newUser, @PathVariable Long id)
    {
        User updatedUser = repository.findById(id) //
                .map(user -> {
                    user.setPassword(new CustomPasswordEncoder().getPasswordEncoder().encode(newUser.getPassword()));
                    return repository.save(user);
                }) //
                .orElseThrow(() -> new EntityNotFoundException(new User(), id));

        EntityModel<User> entityModel = assembler.toModel(updatedUser);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

}
