package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.ShowUserDTO;
import bg.softuni.plantMe.models.DTOs.UserRegisterDTO;
import bg.softuni.plantMe.models.user.PlantMeUserDetails;
import bg.softuni.plantMe.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO createUserRegisterDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("user")
    public ShowUserDTO getUser() {
        return userService.getCurrentUser();
    }
    @GetMapping("/login")
    public String viewLogin () {
        return "login";
    }
    @GetMapping("/register")
    public String viewRegister () {
        return "register";
    }
    @GetMapping("/login-error")
    public String viewLoginError () { return "login-err"; }
    @GetMapping("/profile")
    public String viewProfile () {
        return "profile";
    }
    @PostMapping("/register")
    public String register (@Valid UserRegisterDTO data,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/users/register";
        }

        if (!data.getPassword().equals(data.getConfirmPassword())) {
            ObjectError objectError = new ObjectError("userRegisterDTO", "{password.dont.match}");
            bindingResult.addError(objectError);
            return "redirect:/users/register";
        }
        try {
            userService.register(data);
        } catch (RuntimeException e) {
            return "redirect:/users/register";
        }
        return "redirect:/users/login";
    }
}
