package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.DTOs.UserRegisterDTO;
import bg.softuni.plantMe.models.UserEntity;
import bg.softuni.plantMe.models.UserRole;
import bg.softuni.plantMe.models.enums.UserRoleEnum;
import bg.softuni.plantMe.repository.UserRepository;
import bg.softuni.plantMe.repository.UserRoleRepository;
import bg.softuni.plantMe.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDTO data) {
        UserEntity user = modelMapper.map(data, UserEntity.class);
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.getUserRoles().add(userRoleRepository.findByUserRole(UserRoleEnum.USER)
                .orElseThrow());
        if(data.getUsername().equals("kendata")) {
            user.getUserRoles().add(userRoleRepository.findByUserRole(UserRoleEnum.ADMIN).orElseThrow());
        }
        userRepository.save(user);
    }
}
