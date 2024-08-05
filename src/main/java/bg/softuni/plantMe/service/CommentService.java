package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.Comment;
import bg.softuni.plantMe.models.DTOs.AddCommentDTO;
import bg.softuni.plantMe.models.DTOs.ShowCommentDTO;

import java.util.List;

public interface CommentService {
    List<ShowCommentDTO> getAllCommentsForAttempt (Long attemptId);
    void saveComment (AddCommentDTO addCommentDTO);
    void deleteComment (Long id);
    Comment getCommentById (Long id);
}
