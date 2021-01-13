package ao.adnlogico.multitenant.nuntius.security;

import ao.adnlogico.multitenant.nuntius.tenant.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Domingos M. Fernando
 */
@Service
public class JwtUserDetailsService implements UserDetailsService
{

    private final UserRepository repository;

    public JwtUserDetailsService(UserRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        ao.adnlogico.multitenant.nuntius.tenant.user.User user = repository.findByEmail(userName);
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
