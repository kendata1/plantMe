package bg.softuni.plantMe.repository;

import bg.softuni.plantMe.models.UserRole;
import bg.softuni.plantMe.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByUserRole (UserRoleEnum userRoleEnum);
}
