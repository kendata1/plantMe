package bg.softuni.plantMe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "attempts.api")
public class AttemptsApiConfig {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public AttemptsApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
