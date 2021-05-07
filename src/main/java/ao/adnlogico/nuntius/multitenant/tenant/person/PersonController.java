package ao.adnlogico.nuntius.multitenant.tenant.person;

import ao.adnlogico.nuntius.multitenant.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.tenant.person.Person;
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
public class PersonController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    personService service;
    private final PersonRepository repository;
    private final PersontModelAssembler assembler;

    public PersonController(PersonRepository repository, PersontModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/person")
    public CollectionModel<EntityModel<Person>> all()
    {
        List<EntityModel<Person>> persons = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(persons, linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/person")
    public ResponseEntity<?> save(@RequestBody Person person)
    {
        EntityModel<Person> entityModel = assembler.toModel(repository.save(person));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/person/{id}")
    public EntityModel<Person> findById(@PathVariable Long id)
    {
        Person person = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Person(), id));

        return assembler.toModel(person);
    }

    @RequestAuthorization
    @PutMapping("/person/{id}")
    public ResponseEntity<?> update(@RequestBody Person newPerson, @PathVariable Long id)
    {
        Person updatedperson = repository.findById(id) //
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setBirthdate(newPerson.getBirthdate());
                    person.setIdentityNumber(newPerson.getIdentityNumber());
                    person.setGender(newPerson.getGender());
                    return repository.save(person);
                }) //
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });

        EntityModel<Person> entityModel = assembler.toModel(updatedperson);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
