package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.DTOs.UserRegisterDTO;
import bg.softuni.plantMe.models.UserEntity;
import bg.softuni.plantMe.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDTO data) {
        UserEntity user = modelMapper.map(data, UserEntity.class);
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userRepository.save(user);
    }
}
