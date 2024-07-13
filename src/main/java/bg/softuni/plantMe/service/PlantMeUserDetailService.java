package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.UserEntity;
import bg.softuni.plantMe.models.UserRole;
import bg.softuni.plantMe.models.enums.UserRoleEnum;
import bg.softuni.plantMe.models.user.PlantMeUserDetails;
import bg.softuni.plantMe.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PlantMeUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public PlantMeUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
                return userRepository
                        .findByUsername(username)
                        .map(PlantMeUserDetailService::map)
                        .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private static UserDetails map(UserEntity userEntity) {
        return new PlantMeUserDetails(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getUserRoles().stream().map(UserRole::getUserRole)
                        .map(PlantMeUserDetailService::map).toList(),
                userEntity.getFirstName(),
                userEntity.getLastName()
        );
    }

        private static GrantedAuthority map(UserRoleEnum role) {
            return new SimpleGrantedAuthority(
                    "ROLE_" + role
            );
        }

    }

