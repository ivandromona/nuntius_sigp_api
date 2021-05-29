package ao.adnlogico.nuntius.multitenant.tenant.cataloguing.catalog_data;

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
public class CataloguingDataController implements Serializable
{

    private final CataloguingDataRepository repository;
    private final CataloguingDataModelAssembler assembler;

    public CataloguingDataController(CataloguingDataRepository repository, CataloguingDataModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/cataloguingData")
    public CollectionModel<EntityModel<CataloguingData>> all()
    {
        List<EntityModel<CataloguingData>> cataloguingDatas = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(cataloguingDatas, linkTo(methodOn(CataloguingDataController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/cataloguingData")
    public ResponseEntity<?> save(@RequestBody CataloguingData cataloguingData)
    {
        EntityModel<CataloguingData> entityModel = assembler.toModel(repository.save(cataloguingData));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/cataloguingData/{id}")
    public EntityModel<CataloguingData> findById(@PathVariable Long id)
    {
        CataloguingData cataloguingData = repository.findById(id) //
            .orElseThrow(() -> new EntityNotFoundException(new CataloguingData(), id));

        return assembler.toModel(cataloguingData);
    }

    @RequestAuthorization
    @PutMapping("/cataloguingData/{id}")
    public ResponseEntity<?> update(@RequestBody CataloguingData newCataloguingData, @PathVariable Long id)
    {
        CataloguingData updatedCataloguingData = repository.findById(id) //
            .map(cataloguingData -> {
                cataloguingData.setFkCataloguing(newCataloguingData.getFkCataloguing());
                cataloguingData.setFkCataloguingDictionary(newCataloguingData.getFkCataloguingDictionary());
                return repository.save(cataloguingData);
            }) //
            .orElseGet(() -> {
                newCataloguingData.setId(id);
                return repository.save(newCataloguingData);
            });

        EntityModel<CataloguingData> entityModel = assembler.toModel(updatedCataloguingData);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/cataloguingData/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
