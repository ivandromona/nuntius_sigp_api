package ao.adnlogico.sigp.nuntius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"ao.adnlogico.sigp.nuntius"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@SpringBootApplication
public class NuniusApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(NuniusApplication.class, args);
    }

}
