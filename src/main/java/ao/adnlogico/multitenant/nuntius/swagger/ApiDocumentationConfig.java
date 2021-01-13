/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.multitenant.nuntius.swagger;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

/**
 *
 * @author domingos.fernando
 */
@SwaggerDefinition(
        info = @Info(
                description = "Sistema Integrado de Gestão Processual NUNTIUS v1.0.0",
                version = "V1.0.0",
                title = "S.I.G.P NUNTIUS",
                contact = @Contact(
                        name = "Domingos M. Fernando",
                        email = "domingos.fernando@adnlogico.ao",
                        url = "http://www.nuntius.ao"
                ),
                license = @License(
                        name = "N/A",
                        url = "http://www.nuntius.ao"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Contact ADN Lógico", url = "http://www.nuntius.ao")
)
public class ApiDocumentationConfig
{

}
