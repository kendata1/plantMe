package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.Comment;
import bg.softuni.plantMe.models.DTOs.CommentDTO;
import bg.softuni.plantMe.repository.CommentRepository;
import bg.softuni.plantMe.service.CommentService;
import bg.softuni.plantMe.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void saveComment(CommentDTO commentDTO) {
        commentDTO.setDatePublished(LocalDateTime.now());
        commentRepository.save(modelMapper.map(commentDTO, Comment.class));
    }
    @Override
    public CommentDTO getCommentById(Long id) {
        return commentRepository
                .findById(id)
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .orElseThrow(() -> new ObjectNotFoundException("Comment not found!"));
    }

    @Override
    public List<CommentDTO> getAllCommentsForAttempt(Long attemptId) {
        return commentRepository.findAllByAttemptIdOrderByDatePublished(attemptId)
                .stream().map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteComment (Long id) {
        commentRepository.deleteById(id);
    }


}
