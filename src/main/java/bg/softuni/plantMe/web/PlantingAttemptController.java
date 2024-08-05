package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.AddCommentDTO;
import bg.softuni.plantMe.models.DTOs.PlantingAttemptDTO;
import bg.softuni.plantMe.models.user.PlantMeUserDetails;
import bg.softuni.plantMe.models.user.UserEntity;
import bg.softuni.plantMe.service.CommentService;
import bg.softuni.plantMe.service.PlantService;
import bg.softuni.plantMe.service.PlantingAttemptService;
import bg.softuni.plantMe.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/attempts")
public class PlantingAttemptController {
    private final PlantService plantService;
    private final PlantingAttemptService plantingAttemptService;
    private final CommentService commentService;
    private final UserService userService;


    public PlantingAttemptController(PlantService plantService, PlantingAttemptService plantingAttemptService, CommentService commentService, UserService userService) {
        this.plantService = plantService;
        this.plantingAttemptService = plantingAttemptService;
        this.commentService = commentService;
        this.userService = userService;
    }


    @ModelAttribute("plantingAttemptDTO")
    public PlantingAttemptDTO PlantingAttemptDTO() {
        return new PlantingAttemptDTO();
    }
    @ModelAttribute("plantNames")
    public List<String> plantNames() {
        return plantService.getAllPlantNames();
    }
    @ModelAttribute("user")
    public PlantMeUserDetails getUser() {
       return userService.getCurrentUser();
    }

    @ModelAttribute("addCommentDTO")
    public AddCommentDTO addCommentDTO() {
        return new AddCommentDTO();
    }

    @GetMapping("/add")
    public String viewAddPlantingAttempt () {
        return "add-planting-attempt";
    }

    @PostMapping("add")
    public String addPlantAttempt (@Valid PlantingAttemptDTO plantingAttemptDTO,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   BindingResult bindingResult,
                                   RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("addOfferDTO", plantingAttemptDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.PlantingAttemptDTO", bindingResult);
            return "redirect:/attempts/add";
        }
        plantingAttemptDTO.setUsername(userDetails.getUsername());
        plantingAttemptService.addPlantingAttempt(plantingAttemptDTO);
        return "redirect:/home";
    }

    @GetMapping("/mine")
    public String getAllAttemptsForUser (@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("plantAttemptsForUser", plantingAttemptService.getAllPlantingAttemptsShortForUser(userDetails.getUsername()));

        return "user-planting-attempts";
    }

    @GetMapping("/others")
    public String getAllAttemptsOfOtherUsers (@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("plantAttemptsOfOtherUsers", plantingAttemptService.getAllPlantingAttemptsShortOfOtherUsers(userDetails.getUsername()));

        return "others-planting-attempts";
    }

    @GetMapping("/{id}")
    public String getAttemptDetails (@PathVariable("id") Long id, Model model) {
        PlantingAttemptDTO attemptById = plantingAttemptService.getPlantingAttemptById(id);
        model.addAttribute("currentAttempt", attemptById);
        model.addAttribute("forImg", plantingAttemptService.mapToShortInfo(attemptById));
        model.addAttribute("comments", commentService.getAllCommentsForAttempt(id));

        return "planting-attempt-details";
    }

    @DeleteMapping("/{username}/{id}")
    @PreAuthorize("hasRole('ADMIN') or #userDetails.username == #username")
    public String deleteAttempt (@PathVariable("username") String username,
                                 @PathVariable ("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        plantingAttemptService.deleteAttempt(id);
        return "redirect:/attempts/mine";
    }

}
