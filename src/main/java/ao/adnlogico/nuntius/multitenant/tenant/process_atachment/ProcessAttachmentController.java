package ao.adnlogico.nuntius.multitenant.tenant.process_atachment;

import ao.adnlogico.nuntius.multitenant.tenant.auth.AuthenticationController;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Md. Amran Hossain | amrancse930@gmail.com
 */
@RestController
@RequestMapping("/nuntius/v1/api")
public class ProcessAttachmentController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private ao.adnlogico.nuntius.multitenant.tenant.file.FileStorageService fileStorageService;

    private final ProcessAttachmentRepository repository;
    private final ProcessAttachmentModelAssembler assembler;

    public ProcessAttachmentController(ProcessAttachmentRepository repository, ProcessAttachmentModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @GetMapping("/processAttachment")
    public CollectionModel<EntityModel<ProcessAttachment>> all()
    {
        List<EntityModel<ProcessAttachment>> processAttachments = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(processAttachments, linkTo(methodOn(ProcessAttachmentController.class).all()).withSelfRel());
    }

    @RequestAuthorization
    @PostMapping("/processAttachment")
    public ResponseEntity<?> save(@RequestBody ProcessAttachment processAttachment)
    {
        EntityModel<ProcessAttachment> entityModel = assembler.toModel(repository.save(processAttachment));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @GetMapping("/processAttachment/{id}")
    public EntityModel<ProcessAttachment> findById(@PathVariable Long id)
    {
        ProcessAttachment processAttachment = repository.findById(id) //
            .orElseThrow(() -> new EntityNotFoundException(new ProcessAttachment(), id));

        return assembler.toModel(processAttachment);
    }

    @RequestAuthorization
    @PutMapping("/processAttachment/{id}")
    public ResponseEntity<?> update(@RequestBody ProcessAttachment newprocessAttachment, @PathVariable Long id)
    {
        ProcessAttachment updatedprocessAttachment = repository.findById(id) //
            .map(processAttachment -> {
                processAttachment.setDescription(newprocessAttachment.getDescription());
                return repository.save(processAttachment);
            }) //
            .orElseGet(() -> {
                newprocessAttachment.setId(id);
                return repository.save(newprocessAttachment);
            });

        EntityModel<ProcessAttachment> entityModel = assembler.toModel(updatedprocessAttachment);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @RequestAuthorization
    @DeleteMapping("/processAttachment/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

//    Implementação do file sistem control
    @GetMapping("/processAttachment/files")
    public List listUploadedFiles(Model model) throws IOException
    {

        return fileStorageService.loadAll().map(
            path -> MvcUriComponentsBuilder.fromMethodName(ProcessAttachment.class,
                "serveFile", path.getFileName().toString()).build().toUri().toString())
            .collect(Collectors.toList());
    }

    @RequestAuthorization
    @PostMapping("/attachment/upload")
    public ao.adnlogico.nuntius.multitenant.dto.UploadFileResponse uploadFileSecure(@RequestParam("file") MultipartFile file,
                                                                                    @RequestParam("process") Integer process,
                                                                                    @RequestParam("fileType") String fileType,
                                                                                    @RequestParam("description") String description)
    {
        String fileName = fileStorageService.store(file, new ao.adnlogico.nuntius.multitenant.tenant.process.Process(Long.valueOf(process)), fileType, description);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/nuntius/v1/api/processAttachment/download/")
            .path(fileName)
            .toUriString();

        return new ao.adnlogico.nuntius.multitenant.dto.UploadFileResponse(fileName, fileDownloadUri,
            file.getContentType(), file.getSize());
    }

    @PostMapping("/processAttachment/upload")
    public ao.adnlogico.nuntius.multitenant.dto.UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
                                                                              @RequestParam("process") Integer process,
                                                                              @RequestParam("fileType") String fileType,
                                                                              @RequestParam("description") String description)
    {
        String fileName = fileStorageService.store(file, new ao.adnlogico.nuntius.multitenant.tenant.process.Process(Long.valueOf(process)), fileType, description);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/nuntius/v1/api/processAttachment/download/")
            .path(fileName)
            .toUriString();

        return new ao.adnlogico.nuntius.multitenant.dto.UploadFileResponse(fileName, fileDownloadUri,
            file.getContentType(), file.getSize());
    }

    @GetMapping("/processAttachment/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("process") Integer process,
                                                 @RequestParam("fileType") String fileType,
                                                 HttpServletRequest request)
    {

        String fileName = fileStorageService.getFileName(new ao.adnlogico.nuntius.multitenant.tenant.process.Process(Long.valueOf(process)), fileType);
        Resource resource = null;
        if (fileName != null && !fileName.isEmpty()) {
            try {
                resource = fileStorageService.loadAsResource(fileName);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            // Try to determine file's content type
            String contentType = null;

            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            }
            catch (IOException ex) {
                //logger.info("Could not determine file type.");
            }
            // Fallback to the default content type if type could not be determined
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        }
        else {
            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/processAttachment/showdirect")
    public ResponseEntity<Resource> showFile(@RequestParam("process") Integer process,
                                             @RequestParam("fileType") String fileType,
                                             HttpServletRequest request)
    {

        String fileName = fileStorageService.getFileName(new ao.adnlogico.nuntius.multitenant.tenant.process.Process(Long.valueOf(process)), fileType);
        Resource resource = null;
        if (fileName != null && !fileName.isEmpty()) {
            try {
                resource = fileStorageService.loadAsResource(fileName);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            // Try to determine file's content type
            String contentType = null;

            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            }
            catch (IOException ex) {
                //logger.info("Could not determine file type.");
            }
            // Fallback to the default content type if type could not be determined
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        }
        else {
            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/processAttachments/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws Exception
    {

        Resource file = fileStorageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
