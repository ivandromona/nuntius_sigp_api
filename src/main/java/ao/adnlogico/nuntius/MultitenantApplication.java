package ao.adnlogico.nuntius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableJpaRepositories
@ComponentScan(basePackages = {"ao.adnlogico.nuntius"})
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MultitenantApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(MultitenantApplication.class, args);
    }

}
