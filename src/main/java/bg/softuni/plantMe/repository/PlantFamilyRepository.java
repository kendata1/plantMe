package bg.softuni.plantMe.repository;

import bg.softuni.plantMe.models.PlantFamily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantFamilyRepository extends JpaRepository<PlantFamily, Long> {
    Optional<PlantFamily> findByName (String name);
}
