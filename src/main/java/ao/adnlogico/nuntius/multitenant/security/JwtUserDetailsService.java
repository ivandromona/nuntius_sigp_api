package ao.adnlogico.nuntius.multitenant.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ao.adnlogico.nuntius.multitenant.tenant.repository.UserRepository;

/**
 * @author Domingos M. Fernando
 */
@Component
public class JwtUserDetailsService implements UserDetailsService
{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        ao.adnlogico.nuntius.multitenant.tenant.entity.User user = userRepository.findByEmail(userName);
        if (null == user) {
            throw new UsernameNotFoundException("Invalid user name or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority()
    {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

}
