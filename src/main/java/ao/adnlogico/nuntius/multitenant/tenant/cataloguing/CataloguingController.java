package ao.adnlogico.nuntius.multitenant.tenant.cataloguing;

import ao.adnlogico.nuntius.multitenant.tenant.auth.AuthenticationController;
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
public class CataloguingController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final CataloguingRepository repository;
    private final CataloguingModelAssembler assembler;

    public CataloguingController(CataloguingRepository repository, CataloguingModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/cataloguing")
    public CollectionModel<EntityModel<Cataloguing>> all()
    {
        List<EntityModel<Cataloguing>> cataloguings = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(cataloguings, linkTo(methodOn(CataloguingController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/cataloguing")
    public ResponseEntity<?> save(@RequestBody Cataloguing cataloguing)
    {
        EntityModel<Cataloguing> entityModel = assembler.toModel(repository.save(cataloguing));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/cataloguing/{id}")
    public EntityModel<Cataloguing> findById(@PathVariable Long id)
    {
        Cataloguing cataloguing = repository.findById(id) //
            .orElseThrow(() -> new EntityNotFoundException(new Cataloguing(), id));

        return assembler.toModel(cataloguing);
    }

    @RequestAuthorization
    @PutMapping("/cataloguing/{id}")
    public ResponseEntity<?> update(@RequestBody Cataloguing newCataloguing, @PathVariable Long id)
    {
        Cataloguing updatedCataloguing = repository.findById(id) //
            .map(cataloguing -> {
                cataloguing.setName(newCataloguing.getName());
                cataloguing.setDescription(newCataloguing.getDescription());
                return repository.save(cataloguing);
            }) //
            .orElseGet(() -> {
                newCataloguing.setId(id);
                return repository.save(newCataloguing);
            });

        EntityModel<Cataloguing> entityModel = assembler.toModel(updatedCataloguing);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/cataloguing/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
