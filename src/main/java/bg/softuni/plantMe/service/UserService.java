package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.DTOs.ShowUserDTO;
import bg.softuni.plantMe.models.DTOs.UserRegisterDTO;
import bg.softuni.plantMe.models.user.PlantMeUserDetails;
import bg.softuni.plantMe.models.user.UserEntity;

public interface UserService {
    public void register(UserRegisterDTO userRegisterDTO);

    UserEntity findByUsername (String username);

    ShowUserDTO getCurrentUser ();
}
