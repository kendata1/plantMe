package bg.softuni.plantMe.models.DTOs;

import bg.softuni.plantMe.models.user.UserEntity;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class AddCommentDTO {
        private Long id;
        private LocalDateTime datePublished;
        @Size(min = 5, max = 1200)
        private String text;
        private UserEntity owner;
        private Long attemptId;

        public AddCommentDTO() {}

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public LocalDateTime getDatePublished() {
                return datePublished;
        }

        public void setDatePublished(LocalDateTime datePublished) {
                this.datePublished = datePublished;
        }

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
}
