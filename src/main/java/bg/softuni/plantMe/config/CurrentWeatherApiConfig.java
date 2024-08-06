package bg.softuni.plantMe.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weather.api")
public class CurrentWeatherApiConfig {
    private String url;

    public String getUrl() {
        return this.url;
    }

    public CurrentWeatherApiConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    @PostConstruct
    public void checkConfiguration() {
        verifyNotNullOrEmpty("url", url);
}

    private static void verifyNotNullOrEmpty(String name, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Property " + name + " cannot be empty.");
        }
    }
}
