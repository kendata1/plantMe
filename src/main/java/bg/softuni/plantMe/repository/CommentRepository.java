package bg.softuni.plantMe.repository;

import bg.softuni.plantMe.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByAttemptIdOrderByDatePublished (Long attemptId);
}
