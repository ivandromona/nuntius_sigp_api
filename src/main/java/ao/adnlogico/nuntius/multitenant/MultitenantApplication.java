package ao.adnlogico.nuntius.multitenant;

import ao.adnlogico.nuntius.multitenant.tenant.process_atachment.ProcessAttachment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ProcessAttachment.class)
public class MultitenantApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(MultitenantApplication.class, args);
    }

}
