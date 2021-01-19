/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.swagger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 *
 * @author domingos.fernando
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig
{

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Domingos M. Fernando", "http://www.nuntius.ao", "domingos.fernando@nuntius.ao");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "S.I.G.C PAPE", "Sistema Integrado Gestão Processual Nuntius", "1.0",
            "urn:tos", DEFAULT_CONTACT,
            "N/A", "", Arrays.asList());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES
            = new HashSet<String>(Arrays.asList("application/json",
                    "application/xml"));

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}
