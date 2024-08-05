package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.Comment;
import bg.softuni.plantMe.models.DTOs.AddCommentDTO;
import bg.softuni.plantMe.models.DTOs.ShowCommentDTO;
import bg.softuni.plantMe.repository.CommentRepository;
import bg.softuni.plantMe.service.CommentService;
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
    public List<ShowCommentDTO> getAllCommentsForAttempt(Long attemptId) {
        return commentRepository.findAllByAttemptIdOrderByDatePublished(attemptId)
                .stream().map(comment -> modelMapper.map(comment, ShowCommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveComment(AddCommentDTO addCommentDTO) {
        addCommentDTO.setDatePublished(LocalDateTime.now());
        commentRepository.save(modelMapper.map(addCommentDTO, Comment.class));
    }

    public void deleteComment (Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found!"));
    }
}
