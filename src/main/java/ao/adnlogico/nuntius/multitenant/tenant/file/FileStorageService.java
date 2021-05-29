/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.file;

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

/**
 *
 * @author domingos.fernando
 */
@Service
public class FileStorageService implements StorageService
{

    private final Path rootFileLocation;

    @Autowired
    FileRepository repository;

    @Autowired
    public FileStorageService(FileEntity fileStorageProperties)
    {
        this.rootFileLocation = Paths.get(fileStorageProperties.getUploadDir())
            .toAbsolutePath().normalize();
        init();
    }

    public String getFileName(Long entityId, String fileType)
    {
        return repository.getUploadFilePath(entityId, fileType);
    }

    public String getFileName(Long entityId, String fileType, String entityName)
    {
        return repository.getUploadFilePath(entityId, fileType, entityName);
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
    public String store(MultipartFile file, Long fkEntityId, String fileType, String fileExternalEntity)
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

            fileName = fkEntityId + "_" + fileType + fileExtension;
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.rootFileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            FileEntity fileEntity = repository.checkFileByEntity(fkEntityId, fileType, fileExternalEntity);
            if (fileEntity != null) {
                fileEntity.setFileFormat(file.getContentType());
                fileEntity.setFileName(fileName);
                repository.save(fileEntity);
            }
            else {
                FileEntity newFile = new FileEntity();
                newFile.setFkEntityId(fkEntityId);
                newFile.setFileFormat(file.getContentType());
                newFile.setFileName(fileName);
                newFile.setFileType(fileType);
                newFile.setFileEntity(fileExternalEntity);

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

    public String getFileAbsolutePath(String fileName)
    {

        String filePath = "";
        Resource resource = null;
        try {
            resource = loadAsResource(fileName);
        }
        catch (Exception e) {
        }
        // Try to determine file's content type
        String contentType = null;

        try {
            filePath = resource.getFile().getAbsolutePath();
        }
        catch (IOException ex) {
            //logger.info("Could not determine file type.");
        }
        return filePath;

    }
}
