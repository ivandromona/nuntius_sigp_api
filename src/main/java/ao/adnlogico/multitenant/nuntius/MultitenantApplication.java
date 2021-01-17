package ao.adnlogico.multitenant.nuntius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"ao.adnlogico.multitenant.nuntius"})
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MultitenantApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(MultitenantApplication.class, args);
    }

}
