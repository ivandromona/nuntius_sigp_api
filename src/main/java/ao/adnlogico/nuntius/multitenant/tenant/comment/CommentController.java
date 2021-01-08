package ao.adnlogico.nuntius.multitenant.tenant.comment;

import ao.adnlogico.nuntius.multitenant.tenant.department.*;
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
public class CommentController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    commentService service;
    private final CommentRepository repository;
    private final CommentModelAssembler assembler;

    public CommentController(CommentRepository repository, CommentModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/comment")
    public CollectionModel<EntityModel<Comment>> all()
    {
        List<EntityModel<Comment>> comments = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(comments, linkTo(methodOn(CommentController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/comment")
    public ResponseEntity<?> save(@RequestBody Comment comment)
    {
        EntityModel<Comment> entityModel = assembler.toModel(repository.save(comment));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/comment/{id}")
    public EntityModel<Comment> findById(@PathVariable Integer id)
    {
        Comment comment = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(new Comment(), id));

        return assembler.toModel(comment);
    }

    @RequestAuthorization
    @PutMapping("/comment/{id}")
    public ResponseEntity<?> update(@RequestBody Comment newcomment, @PathVariable Integer id)
    {
        Comment updatedcomment = repository.findById(id) //
                .map(comment -> {
                    comment.setComment(newcomment.getComment());
                    return repository.save(comment);
                }) //
                .orElseGet(() -> {
                    newcomment.setId(id);
                    return repository.save(newcomment);
                });

        EntityModel<Comment> entityModel = assembler.toModel(updatedcomment);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
