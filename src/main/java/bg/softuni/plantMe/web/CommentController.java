package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.CommentDTO;
import bg.softuni.plantMe.service.CommentService;
import bg.softuni.plantMe.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("attempts/{id}/add-comment")
    public String addComment (@PathVariable("id") Long id, @Valid CommentDTO addCommentDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addCommentDTO", addCommentDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addCommentDTO", bindingResult);
            return "redirect:/attempts/" + id;
        }

        addCommentDTO.setAttemptId(id);
        addCommentDTO.setOwner(userService.findByUsername(userDetails.getUsername()));

        commentService.saveComment(addCommentDTO);

        return "redirect:/attempts/" + id;
    }

    @DeleteMapping("comments/{username}/{id}")
    @PreAuthorize("hasRole('ADMIN') or #userDetails.username == #username")
    public String deleteComment (@PathVariable("id") Long id,
                                 @PathVariable("username") String username,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        Long attemptId = commentService.getCommentById(id).getAttemptId();
        commentService.deleteComment(id);
        return "redirect:/attempts/" + attemptId;
    }
}
