package bg.softuni.plantMe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlantMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantMeApplication.class, args);
	}

}
