package bg.softuni.plantMe.repository;

import bg.softuni.plantMe.models.Plant;
import bg.softuni.plantMe.models.PlantFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findPlantsByPlantFamily (PlantFamily plantFamily);

    Optional<Plant> findByName (String plantName);
}
