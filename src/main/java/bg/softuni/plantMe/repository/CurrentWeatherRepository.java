package bg.softuni.plantMe.repository;

import bg.softuni.plantMe.models.CurrentWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<CurrentWeather, Long> {
}
