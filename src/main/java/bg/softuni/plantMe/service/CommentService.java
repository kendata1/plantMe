package bg.softuni.plantMe.service;

import bg.softuni.plantMe.models.DTOs.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getAllCommentsForAttempt (Long attemptId);
    void saveComment (CommentDTO addCommentDTO);
    void deleteComment (Long id);
    CommentDTO getCommentById (Long id);
}
