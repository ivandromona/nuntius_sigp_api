/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ao.adnlogico.nuntius.multitenant.tenant.doc_template;

import ao.adnlogico.nuntius.multitenant.util.Converter;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class DocTemplateService {

    @Autowired
    private Converter conversor;

    @Autowired
    private DocTemplateRepository repository;

    public DocTemplate create(DocTemplate docTemplate) throws Exception {
        File file = new File(docTemplate.getFileUrl());
        docTemplate.setStandardContent(conversor.fromDocxToHtmlAndBack(file));
        return repository.save(docTemplate);
    }

}
