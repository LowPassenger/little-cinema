package cinema.service;

import lombok.extern.log4j.Log4j2;
import static org.springframework.security.core.userdetails.User.withUsername;

import cinema.model.Role;
import cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userService.findByEmail(username).isEmpty()) {
            log.error("Can't find User by email. Params: email = {}", username);
            throw new UsernameNotFoundException("User with username " + username
                    + " not found!");
        }
        User user = userService.findByEmail(username).get();
        UserBuilder userBuilder =
                withUsername(username);
        userBuilder.password(user.getPassword());
        userBuilder.authorities(user.getRoles()
                .stream()
                .map(Role::getRoleName)
                        .map(Enum::toString)
                .toArray(String[]::new));
        log.info("Create User Details. Params: User = {}", user);
        return userBuilder.build();
    }
}
