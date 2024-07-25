package bg.softuni.plantMe.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class AppConfig {

    @Bean
    ModelMapper modelMapper () {
        return new ModelMapper();
    }

    @Bean
    Gson gson () {
        return new Gson();
    }
}

