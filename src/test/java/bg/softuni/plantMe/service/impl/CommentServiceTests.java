package bg.softuni.plantMe.service.impl;

import bg.softuni.plantMe.models.Comment;
import bg.softuni.plantMe.models.DTOs.CommentDTO;
import bg.softuni.plantMe.repository.CommentRepository;
import bg.softuni.plantMe.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTests {

    private CommentServiceImpl toTest;

    @Mock
    private CommentRepository mockCommentRepository;

    @Captor
    private ArgumentCaptor<Comment> commentArgumentCaptor;
    @BeforeEach
    void setUp () {
        toTest = new CommentServiceImpl(
                mockCommentRepository,
                new ModelMapper()
        );
    }
        @Test
        void testSaveComment () {

            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setDatePublished(LocalDateTime.now());
            commentDTO.setText("Lorem ipsum odor amet, consectetuer adipiscing elit. Feugiat vehicula aliquam duis;");
            commentDTO.setAttemptId(1L);
            commentDTO.setId(1L);

            toTest.saveComment(commentDTO);

            // Assert
            verify(mockCommentRepository).save(commentArgumentCaptor.capture());

            Comment actualSavedPlant = commentArgumentCaptor.getValue();

            Assertions.assertEquals(commentDTO.getDatePublished(), actualSavedPlant.getDatePublished());
            Assertions.assertEquals(commentDTO.getText(), actualSavedPlant.getText());
            Assertions.assertEquals(commentDTO.getAttemptId(), actualSavedPlant.getAttemptId());
            Assertions.assertEquals(commentDTO.getId(), actualSavedPlant.getId());
        }

        @Test
        void testGetCommentById_Exists () {
        Comment testComment = new Comment();
            testComment.setDatePublished(LocalDateTime.now());
            testComment.setText("Lorem ipsum odor amet, consectetuer adipiscing elit. Feugiat vehicula aliquam duis;");
            testComment.setAttemptId(1L);
            testComment.setId(1L);

            when(mockCommentRepository.findById(testComment.getId())).thenReturn(Optional.of(testComment));

            CommentDTO commentDTO = toTest.getCommentById(1L);

            Assertions.assertEquals(commentDTO.getDatePublished(), testComment.getDatePublished());
            Assertions.assertEquals(commentDTO.getText(), testComment.getText());
            Assertions.assertEquals(commentDTO.getAttemptId(), testComment.getAttemptId());
            Assertions.assertEquals(commentDTO.getId(), testComment.getId());
        }

    @Test
    void testGetCommentById_NotExists () {
        Assertions.assertThrows(ObjectNotFoundException.class,
                ()-> toTest.getCommentById(1L));
    }


}
