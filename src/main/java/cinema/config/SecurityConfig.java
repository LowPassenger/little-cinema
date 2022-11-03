package cinema.config;

import cinema.model.Role;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USER_ROLE = String.valueOf(Role.RoleName.USER);
    private static final String ADMIN_ROLE = String.valueOf(Role.RoleName.ADMIN);
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        log.debug("AuthenticationManagerBuilder configure");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/inject").permitAll()
                .antMatchers(HttpMethod.GET, "/movies/**").hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/movies/**").hasAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/cinema-halls/**")
                .hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/cinema-halls/**").hasAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/movie-sessions/**")
                .hasAnyAuthority(USER_ROLE, ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/movie-sessions/**").hasAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.PUT, "/movie-sessions/**").hasAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/**").hasAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/orders/**").hasAuthority(USER_ROLE)
                .antMatchers(HttpMethod.POST, "/orders/**").hasAuthority(USER_ROLE)
                .antMatchers(HttpMethod.GET, "/shopping-carts/**").hasAuthority(USER_ROLE)
                .antMatchers(HttpMethod.POST, "/shopping-carts/**").hasAuthority(USER_ROLE)
                .antMatchers(HttpMethod.GET, "/users/**").hasAuthority(ADMIN_ROLE)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
        log.debug("Http Security configure OK.");
    }
}
