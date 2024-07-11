package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.UserEntity;
import bg.softuni.plantMe.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
                return userRepository
                        .findByUsername(username)
                        .map(UserDetailService::map)
                        .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private static UserDetails map(UserEntity userEntity) {
    return User
            .withUsername(userEntity.getUsername())
            .password(userEntity.getPassword())
            .authorities(List.of()) /*TODO*/
            .disabled(false)
            .build();
    }
}
