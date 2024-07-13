package bg.softuni.plantMe.repository;

import bg.softuni.plantMe.models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}
