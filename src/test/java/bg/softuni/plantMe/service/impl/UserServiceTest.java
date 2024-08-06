package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.DTOs.UserRegisterDTO;
import bg.softuni.plantMe.models.enums.UserRoleEnum;
import bg.softuni.plantMe.models.user.UserEntity;
import bg.softuni.plantMe.models.user.UserRole;
import bg.softuni.plantMe.repository.UserRepository;
import bg.softuni.plantMe.repository.UserRoleRepository;
import bg.softuni.plantMe.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserServiceImpl toTest;
    @Captor
    private ArgumentCaptor<UserEntity> userEntityArgumentCaptor;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private UserRoleRepository mockUserRoleRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp () {
        toTest=new UserServiceImpl(
                mockUserRepository,
                mockUserRoleRepository,
                new ModelMapper(),
                mockPasswordEncoder
        );
    }

    @Test
    void testUserRegistration() {
        // Arrange

        UserRegisterDTO userRegistrationDTO = new UserRegisterDTO();
        userRegistrationDTO.setFirstName("Test");
        userRegistrationDTO.setLastName("Testov");
        userRegistrationDTO.setUsername("username");
        userRegistrationDTO.setEmail("test@abv.bg");
        userRegistrationDTO.setPassword("parola123");
        userRegistrationDTO.setConfirmPassword("parola123");

        when(mockPasswordEncoder.encode(userRegistrationDTO.getPassword()))
                .thenReturn(userRegistrationDTO.getPassword()+userRegistrationDTO.getPassword());
        when(mockUserRoleRepository.findByUserRole(UserRoleEnum.USER))
                .thenReturn(Optional.of(new UserRole(1, UserRoleEnum.USER)));

        // ACT
        toTest.register(userRegistrationDTO);

        // Assert
        verify(mockUserRepository).save(userEntityArgumentCaptor.capture());

        UserEntity actualSavedEntity = userEntityArgumentCaptor.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(userRegistrationDTO.getFirstName(), actualSavedEntity.getFirstName());
        Assertions.assertEquals(userRegistrationDTO.getLastName(), actualSavedEntity.getLastName());
        Assertions.assertEquals(userRegistrationDTO.getUsername(), actualSavedEntity.getUsername());
        Assertions.assertEquals(userRegistrationDTO.getPassword() + userRegistrationDTO.getPassword(),
                actualSavedEntity.getPassword());
        Assertions.assertEquals(userRegistrationDTO.getEmail(), actualSavedEntity.getEmail());
    }
}
