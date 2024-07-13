package bg.softuni.plantMe.repository;

import bg.softuni.plantMe.models.PlantFamily;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantFamilyRepository extends JpaRepository<PlantFamily, Long> {
}
