/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.file;

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

//    String store(MultipartFile file, Integer userId, String fileType);
    String store(MultipartFile file, Long fkEntityId, String fileType, String fileEntity);

    Stream<Path> loadAll();

    Path load(String fileName);

    Resource loadAsResource(String fileName) throws Exception;

    void deleteAll();

}
