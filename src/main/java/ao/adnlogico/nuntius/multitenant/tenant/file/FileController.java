/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.file;

import ao.adnlogico.nuntius.multitenant.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 *
 * @author domingos.fernando
 */
@RestController
@RequestMapping(Constants.DEFAULT_APP_URL_BASE + Constants.DEFAULT_APP_API_VERSION + Constants.APP_NAME)
public class FileController
{

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/file")
    public List listUploadedFiles(Model model) throws IOException
    {

        return fileStorageService.loadAll().map(
            path -> MvcUriComponentsBuilder.fromMethodName(FileController.class,
                "serveFile", path.getFileName().toString()).build().toUri().toString())
            .collect(Collectors.toList());
    }

    @PostMapping("/file/upload")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
                                         @RequestParam("entityId") Long entityId,
                                         @RequestParam("fileType") String fileType)
    {
        String fileName = fileStorageService.store(file, entityId, fileType, "");

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            //            .path("/sigcpape/v1/api/file/download/")
            .path(Constants.DEFAULT_APP_URL_BASE + "/v1/api/files/show/")
            .path(fileName)
            .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
            file.getContentType(), file.getSize());
    }

    @GetMapping("/file/showdirect")
    public ResponseEntity<Resource> showFile(@RequestParam("entityId") Long entityId,
                                             @RequestParam("fileType") String fileType,
                                             HttpServletRequest request)
    {

        String fileName = fileStorageService.getFileName(entityId, fileType);
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

    @GetMapping("/file/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("entityId") Long entityId,
                                                 @RequestParam("fileType") String fileType,
                                                 HttpServletRequest request)
    {

        String fileName = fileStorageService.getFileName(entityId, fileType);
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

    @GetMapping("/files/show/{filename:.+}")
//    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, HttpServletRequest request) throws Exception
    {

        Resource resource = null;
        try {
            resource = fileStorageService.loadAsResource(filename);
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

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFileContent(@PathVariable String filename) throws Exception
    {

        Resource file = fileStorageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    //    Implementa????o do file sistem control
//    @GetMapping("/processAttachment/files")
//    public List listUploadedFiles(Model model) throws IOException
//    {
//
//        return fileStorageService.loadAll().map(
//            path -> MvcUriComponentsBuilder.fromMethodName(ProcessAttachment.class,
//                "serveFile", path.getFileName().toString()).build().toUri().toString())
//            .collect(Collectors.toList());
//}
//
//    @RequestAuthorization
//    @PostMapping("/attachment/upload")
//    public ao.adnlogico.nuntius.multitenant.tenant.file.UploadFileResponse uploadFileSecure(@RequestParam("file") MultipartFile file,
//                                                                                            @RequestParam("process") Long process,
//                                                                                            @RequestParam("fileType") String fileType,
//                                                                                            @RequestParam("description") String description)
//    {
//        String fileName = fileStorageService.store(file, process, fileType, description);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//            .path("/nuntius/v1/api/processAttachment/download/")
//            .path(fileName)
//            .toUriString();
//
//        return new ao.adnlogico.nuntius.multitenant.tenant.file.UploadFileResponse(fileName, fileDownloadUri,
//            file.getContentType(), file.getSize());
//    }
//
//    @PostMapping("/processAttachment/upload")
//    public ao.adnlogico.nuntius.multitenant.tenant.file.UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
//                                                                                      @RequestParam("process") Long process,
//                                                                                      @RequestParam("fileType") String fileType,
//                                                                                      @RequestParam("description") String description)
//    {
//        String fileName = fileStorageService.store(file, process, fileType, description);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//            .path("/nuntius/v1/api/processAttachment/download/")
//            .path(fileName)
//            .toUriString();
//
//        return new ao.adnlogico.nuntius.multitenant.tenant.file.UploadFileResponse(fileName, fileDownloadUri,
//            file.getContentType(), file.getSize());
//    }
//
//    @GetMapping("/processAttachment/download")
//    public ResponseEntity<Resource> downloadFile(@RequestParam("atache") Long atacheId,
//                                                 @RequestParam("fileType") String fileType,
//                                                 HttpServletRequest request)
//    {
//
//        String fileName = fileStorageService.getFileName(atacheId, fileType, ProcessAttachment.class.getName());
//        Resource resource = null;
//        if (fileName != null && !fileName.isEmpty()) {
//            try {
//                resource = fileStorageService.loadAsResource(fileName);
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//            // Try to determine file's content type
//            String contentType = null;
//
//            try {
//                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//            }
//            catch (IOException ex) {
//                //logger.info("Could not determine file type.");
//            }
//            // Fallback to the default content type if type could not be determined
//            if (contentType == null) {
//                contentType = "application/octet-stream";
//            }
//
//            return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//        }
//        else {
//            return ResponseEntity.notFound().build();
//
//        }
//
//    }
//
//    @GetMapping("/processAttachment/showdirect")
//    public ResponseEntity<Resource> showFile(@RequestParam("process") Long process,
//                                             @RequestParam("fileType") String fileType,
//                                             HttpServletRequest request)
//    {
//
//        String fileName = fileStorageService.getFileName(process, fileType);
//        Resource resource = null;
//        if (fileName != null && !fileName.isEmpty()) {
//            try {
//                resource = fileStorageService.loadAsResource(fileName);
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//            // Try to determine file's content type
//            String contentType = null;
//
//            try {
//                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//            }
//            catch (IOException ex) {
//                //logger.info("Could not determine file type.");
//            }
//            // Fallback to the default content type if type could not be determined
//            if (contentType == null) {
//                contentType = "application/octet-stream";
//            }
//
//            return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//        }
//        else {
//            return ResponseEntity.notFound().build();
//
//        }
//
//    }
//
//    @GetMapping("/processAttachments/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws Exception
//    {
//
//        Resource file = fileStorageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//            "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
}
