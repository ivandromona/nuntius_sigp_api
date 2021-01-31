/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.dto;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 *
 * @author domingos.fernando
 */
public interface StorageService
{

    void init();

    String store(MultipartFile file, ao.adnlogico.nuntius.multitenant.tenant.process.Process process, String fileType);

    Stream<Path> loadAll();

    Path load(String fileName);

    Resource loadAsResource(String fileName) throws Exception;

    void deleteAll();

}
