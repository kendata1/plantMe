package bg.softuni.plantMe.repository;

import bg.softuni.plantMe.models.PlantFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PlantFamilyRepository extends JpaRepository<PlantFamily, Long> {
    Optional<PlantFamily> findByName (String name);
}
