package bg.softuni.plantMe.models.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank(message = "{not.blank.err}")
    @Size(min = 3, max = 20, message = "{register.size.err}")
    private String username;
    @NotBlank(message = "{not.blank.err}")
    @Size(min = 3, max = 20, message = "{register.size.err}")
    private String password;

    public UserLoginDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
