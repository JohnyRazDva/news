package by.academy.news.service.user;

import by.academy.news.entity.user.User;
import by.academy.news.repository.user.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eugene Petrov
 */
@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDetailsRepository.findUserByUsername(username);
        UserBuilder builder = null;
        if (user != null) {

            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            String[] authorities = user.getAuthorities()
                    .stream().map(a -> a.getAuthority()).toArray(String[]::new);
            builder.authorities(authorities);
            builder.roles(user.getRole());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
}
