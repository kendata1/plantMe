package bg.softuni.plantMe.models.user;

import bg.softuni.plantMe.models.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;

    public UserRole () {}

    public long getId() {
        return id;
    }

    public UserRole setId(long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public UserRole setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }
}
