package bg.softuni.plantMe.models.DTOs;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
    @NotBlank(message = "{not.blank.err}")
    @Size(min = 3, max = 20, message = "{register.size.err}")
    String username;
    @Email(message = "{email.field.err}")
    @NotBlank(message = "{not.blank.err}")
    String email;
    @NotBlank(message = "{not.blank.err}")
    @Size(min = 3, max = 20, message = "{register.size.err}")
    String password;
    @NotBlank(message = "{not.blank.err}")
    @Size(min = 3, max = 20, message = "{register.size.err}")
    String confirmPassword;
    @NotBlank(message = "{not.blank.err}")
    @Size(min = 3, max = 20, message = "{register.size.err}")
    String firstName;
    @NotBlank(message = "{not.blank.err}")
    @Size(min = 3, max = 20, message = "{register.size.err}")
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
