package bg.softuni.plantMe.models;

import bg.softuni.plantMe.models.base.BaseEntity;
import bg.softuni.plantMe.models.user.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Table(name = "comments")
@Entity
public class Comment extends BaseEntity {
    @Column(name = "date_published")
    private LocalDateTime datePublished;
    @Column(columnDefinition = "TEXT")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity owner;
    @Column(name = "attemptId", nullable = false)
    private Long attemptId;

    public Comment () {}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public Long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }

    public LocalDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDateTime datePublished) {
        this.datePublished = datePublished;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "datePublished=" + datePublished +
                ", text='" + text + '\'' +
                ", owner=" + owner +
                ", attemptId=" + attemptId +
                '}';
    }
}
