package bg.softuni.plantMe.repository;

import bg.softuni.plantMe.models.PlantingAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantingAttemptRepository extends JpaRepository<PlantingAttempt, Long> {
}
