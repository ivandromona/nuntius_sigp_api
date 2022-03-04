/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ao.adnlogico.nuntius.multitenant.util;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.convert.out.html.HTMLConversionImageHandler;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Component;

/**
 *
 * @author PC
 */
@Component
public class Converter {

    public String fromDocxToHtmlAndBack(File file) throws Exception {

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);
        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
        htmlSettings.setWmlPackage(wordMLPackage.getPackage());
        // htmlSettings.setImageDirPath("./imagem");
        // htmlSettings.setImageTargetUri("./imagem");
        HTMLConversionImageHandler imageHandler = new HTMLConversionImageHandler("", "", false);
        htmlSettings.setImageHandler(imageHandler);
        String htmlFilePath = "./novo.html";
        OutputStream os = new java.io.FileOutputStream(htmlFilePath);
        // System.out.print(texto);
        // write html
        Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
        // Save in BD
        os.flush();
        //file.delete();
        return transformList(Files.readAllLines(Paths.get(htmlFilePath)));
    }

    private String transformList(List<String> stringList) {
        String result = "";
        for (String content : stringList) {
            result += content;
        }
        String a = replace(result, "\\\"", "\"");
        return replace(a, "\"", "\'");
    }

    public String replace(String texto, String searchCaracter, String newCaracter) {

        return texto.replace(searchCaracter, newCaracter);
    }

}
