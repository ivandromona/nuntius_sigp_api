/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.process_atachment;

import ao.adnlogico.nuntius.multitenant.dto.StorageService;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import org.springframework.util.FileSystemUtils;
import ao.adnlogico.nuntius.multitenant.tenant.process.Process;
import ao.adnlogico.nuntius.multitenant.tenant.process.ProcessRepository;
import java.util.Calendar;

/**
 *
 * @author domingos.fernando
 */
@Service
public class FileStorageService implements StorageService
{

    private final Path rootFileLocation;

    @Autowired
    ProcessAttachmentRepository repository;
    @Autowired
    ProcessRepository processRepository;

    @Autowired
    public FileStorageService(ProcessAttachment fileStorageProperties)
    {
        this.rootFileLocation = Paths.get(fileStorageProperties.getUploadDir())
            .toAbsolutePath().normalize();
        System.err.println("\nUpload DIrr: " + this.rootFileLocation + "\n");
        init();
    }

    public String getFileName(Process process, String fileType)
    {
        return repository.getUploadFilePath(process, fileType);
    }

    @Override
    public final void init()
    {
        try {
            Files.createDirectories(this.rootFileLocation);
        }
        catch (IOException ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String store(MultipartFile file, Process process, String fileType, String description)
    {
        // Normalize file name
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = "";

        try {
            // Check if the file's name contains invalid characters
            if (originalFileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + originalFileName);
            }
            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            catch (Exception e) {
                fileExtension = "";
            }

            final Process localProcess = processRepository.findById(process.getId()).orElseThrow(() -> new EntityNotFoundException(new Process(), process.getId()));
            fileName = localProcess.getProcessNumber() + "_" + fileType + fileExtension;
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.rootFileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            ProcessAttachment fileEntity = repository.checkFileByProcess(process, fileType);
            if (fileEntity != null) {
                fileEntity.setExtension(file.getContentType());
                fileEntity.setName(fileName);
                fileEntity.setDescription(description);
                repository.save(fileEntity);
            }
            else {
                ProcessAttachment newFile = new ProcessAttachment();
                newFile.setFkProcess(process);
                newFile.setExtension(file.getContentType());
                newFile.setName(fileName);
                newFile.setFile(fileType);
                newFile.setDescription(description);
                newFile.setCreatedAt(Calendar.getInstance().getTime());
                repository.save(newFile);
            }

            return fileName;
        }
        catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public String store(MultipartFile file, Process process, String fileType)
    {
        // Normalize file name
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = "";

        try {
            // Check if the file's name contains invalid characters
            if (originalFileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + originalFileName);
            }
            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            catch (Exception e) {
                fileExtension = "";
            }

            fileName = process.getProcessNumber() + "_" + fileType + fileExtension;
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.rootFileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            ProcessAttachment fileEntity = repository.checkFileByProcess(process, fileType);
            if (fileEntity != null) {
                fileEntity.setExtension(file.getContentType());
                fileEntity.setName(fileName);
                repository.save(fileEntity);
            }
            else {
                ProcessAttachment newFile = new ProcessAttachment();
                newFile.setFkProcess(process);
                newFile.setExtension(file.getContentType());
                newFile.setName(fileName);
                newFile.setFile(fileType);
                newFile.setCreatedAt(Calendar.getInstance().getTime());
                repository.save(newFile);
            }

            return fileName;
        }
        catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Stream<Path> loadAll()
    {
        try {
            return Files.walk(this.rootFileLocation, 1)
                .filter(path -> !path.equals(this.rootFileLocation))
                .map(this.rootFileLocation::relativize);
        }
        catch (IOException e) {
            throw new FileStorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename)
    {
        return rootFileLocation.resolve(filename);
    }

    /**
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    @Override
    public Resource loadAsResource(String fileName) throws Exception
    {
        try {
            Path filePath = this.rootFileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            }
            else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        }
        catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

    @Override
    public void deleteAll()
    {
        FileSystemUtils.deleteRecursively(rootFileLocation.toFile());
    }
}
