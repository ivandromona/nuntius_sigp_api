package ao.adnlogico.multitenant.nuntius.security;

import java.lang.annotation.*;

/**
 * @author Domingos M. Fernando
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequestAuthorization
{
}