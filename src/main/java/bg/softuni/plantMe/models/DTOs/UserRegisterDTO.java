package bg.softuni.plantMe.models.DTOs;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    String username;
    @Email
    @NotBlank
    String email;
    @NotBlank
    @Size(min = 3, max = 20)
    String password;
    @NotBlank
    @Size(min = 3, max = 20)
    String confirmPassword;

    @NotBlank
    @Size(min = 3, max = 20)
    String firstName;
    @NotBlank
    @Size(min = 3, max = 20)
    String lastName;

    public UserRegisterDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
