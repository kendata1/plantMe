package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.enums.UserRoleEnum;
import bg.softuni.plantMe.models.user.PlantMeUserDetails;
import bg.softuni.plantMe.models.user.UserEntity;
import bg.softuni.plantMe.models.user.UserRole;
import bg.softuni.plantMe.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlantMeUserDetailsServiceTests {

    private static final String TEST_USERNAME = "test";
    private static final String NOT_EXIST_USERNAME = "notExist";

    private PlantMeUserDetailService toTest;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        toTest = new PlantMeUserDetailService(userRepository);
    }

    @Test
    void loadUserByUsername_UserFound () {

        UserEntity testUser = new UserEntity();
        testUser.setUsername(TEST_USERNAME);
        testUser.setEmail("email@abv.bg");
        testUser.setPassword("password");
        testUser.setFirstName("First");
        testUser.setLastName("Last");
        testUser.setUserRoles(List.of(
                new UserRole().setUserRole(UserRoleEnum.ADMIN),
                new UserRole().setUserRole(UserRoleEnum.USER)
        ));
        when(userRepository.findByUsername(TEST_USERNAME))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = toTest.loadUserByUsername(TEST_USERNAME);

        Assertions.assertInstanceOf(PlantMeUserDetails.class, userDetails);
        PlantMeUserDetails plantMeUserDetails = (PlantMeUserDetails) userDetails;

        Assertions.assertEquals(testUser.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getFirstName(),plantMeUserDetails.getFirstName());
        Assertions.assertEquals(testUser.getLastName(),plantMeUserDetails.getLastName());
        Assertions.assertEquals(testUser.getPassword(),plantMeUserDetails.getPassword());

        var expectedRoles = testUser.getUserRoles().stream().map(UserRole::getUserRole).map(r -> "ROLE_" + r).toList();
        var actualRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Assertions.assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void loadUserByUsername_UserNotFound () {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () ->toTest.loadUserByUsername(NOT_EXIST_USERNAME)
        );
    }


}
