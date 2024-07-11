package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.UserRegisterDTO;
import bg.softuni.plantMe.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/login")
    public String viewLogin () {
        return "login";
    }

    @GetMapping("/register")
    public String register () {
        return "register";
    }
    @PostMapping("/register")
    public String register (@Valid UserRegisterDTO data,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !data.getPassword().equals(data.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/register";
        }

        try {
            userService.register(data);
            return "redirect:/login";
        } catch (RuntimeException e) {
            return "redirect:/register";
        }
    }
}
