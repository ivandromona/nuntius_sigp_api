package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.dictionary;

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
public class CataloguingDictionaryController implements Serializable
{

    private final CataloguingDictionaryRepository repository;
    private final CataloguingDictionaryModelAssembler assembler;

    public CataloguingDictionaryController(CataloguingDictionaryRepository repository, CataloguingDictionaryModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/cataloguingDictionary")
    public CollectionModel<EntityModel<CataloguingDictionary>> all()
    {
        List<EntityModel<CataloguingDictionary>> cataloguingDictionarys = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(cataloguingDictionarys, linkTo(methodOn(CataloguingDictionaryController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/cataloguingDictionary")
    public ResponseEntity<?> save(@RequestBody CataloguingDictionary cataloguingDictionary)
    {
        EntityModel<CataloguingDictionary> entityModel = assembler.toModel(repository.save(cataloguingDictionary));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/cataloguingDictionary/{id}")
    public EntityModel<CataloguingDictionary> findById(@PathVariable Long id)
    {
        CataloguingDictionary cataloguingDictionary = repository.findById(id) //
            .orElseThrow(() -> new EntityNotFoundException(new CataloguingDictionary(), id));

        return assembler.toModel(cataloguingDictionary);
    }

    @RequestAuthorization
    @PutMapping("/cataloguingDictionary/{id}")
    public ResponseEntity<?> update(@RequestBody CataloguingDictionary newCataloguingDictionary, @PathVariable Long id)
    {
        CataloguingDictionary updatedCataloguingDictionary = repository.findById(id) //
            .map(cataloguingDictionary -> {
                cataloguingDictionary.setName(newCataloguingDictionary.getName());
                cataloguingDictionary.setDescription(newCataloguingDictionary.getDescription());
                return repository.save(cataloguingDictionary);
            }) //
            .orElseGet(() -> {
                newCataloguingDictionary.setId(id);
                return repository.save(newCataloguingDictionary);
            });

        EntityModel<CataloguingDictionary> entityModel = assembler.toModel(updatedCataloguingDictionary);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/cataloguingDictionary/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
