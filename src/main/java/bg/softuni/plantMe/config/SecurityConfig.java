package bg.softuni.plantMe.config;

import bg.softuni.plantMe.repository.UserRepository;
import bg.softuni.plantMe.service.impl.PlantMeUserDetailService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity
                .authorizeHttpRequests(
                // Setup which URL-s are available for who
                 authorizeRequests ->
                         authorizeRequests
                                 // "all static resources to common locations" (css, images etc.) are available for anyone
                                 .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                 // some more resources for all users
                                 .requestMatchers("/", "users/login", "users/register", "users/login-error").permitAll()
                                 .requestMatchers("/add-plant", "/add-plant-family").hasRole("ADMIN")
                                 // all other URL-s should be authenticated
                                 .anyRequest().authenticated()
        )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/users/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/home", true)
                                .failureUrl("/users/login-error")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/users/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                )
                .build();
    }
    @Bean
    public PlantMeUserDetailService userDetailService(UserRepository userRepository) {
        return new PlantMeUserDetailService(userRepository);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
