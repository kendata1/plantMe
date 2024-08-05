package bg.softuni.plantMe.models.DTOs;

import bg.softuni.plantMe.models.user.UserEntity;

import java.time.LocalDateTime;

public class ShowCommentDTO {
    private Long id;
    private String text;
    private UserEntity owner;
    private LocalDateTime datePublished;

    public ShowCommentDTO () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDateTime datePublished) {
        this.datePublished = datePublished;
    }
}
