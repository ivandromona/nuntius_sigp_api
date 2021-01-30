/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author domingos.fernando
 */
@Component
public class CustomPasswordEncoder
{

    public CustomPasswordEncoder()
    {
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
