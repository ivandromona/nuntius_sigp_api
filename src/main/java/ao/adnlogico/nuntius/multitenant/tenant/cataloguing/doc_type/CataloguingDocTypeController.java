package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.doc_type;

import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
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
public class CataloguingDocTypeController implements Serializable
{

    private final CataloguingDocTypeRepository repository;
    private final CataloguingDocTypeModelAssembler assembler;

    public CataloguingDocTypeController(CataloguingDocTypeRepository repository, CataloguingDocTypeModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/cataloguingDocType")
    public CollectionModel<EntityModel<CataloguingDocType>> all()
    {
        List<EntityModel<CataloguingDocType>> cataloguingDocTypes = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(cataloguingDocTypes, linkTo(methodOn(CataloguingDocTypeController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/cataloguingDocType")
    public ResponseEntity<?> save(@RequestBody CataloguingDocType cataloguingDocType)
    {
        EntityModel<CataloguingDocType> entityModel = assembler.toModel(repository.save(cataloguingDocType));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/cataloguingDocType/{id}")
    public EntityModel<CataloguingDocType> findById(@PathVariable Long id)
    {
        CataloguingDocType cataloguingDocType = repository.findById(id) //
            .orElseThrow(() -> new EntityNotFoundException(new CataloguingDocType(), id));

        return assembler.toModel(cataloguingDocType);
    }

    @RequestAuthorization
    @PutMapping("/cataloguingDocType/{id}")
    public ResponseEntity<?> update(@RequestBody CataloguingDocType newCataloguingDocType, @PathVariable Long id)
    {
        CataloguingDocType updatedCataloguingDocType = repository.findById(id) //
            .map(cataloguingDocType -> {
                cataloguingDocType.setName(newCataloguingDocType.getName());
                cataloguingDocType.setDescription(newCataloguingDocType.getDescription());
                return repository.save(cataloguingDocType);
            }) //
            .orElseGet(() -> {
                newCataloguingDocType.setId(id);
                return repository.save(newCataloguingDocType);
            });

        EntityModel<CataloguingDocType> entityModel = assembler.toModel(updatedCataloguingDocType);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/cataloguingDocType/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
