package bg.softuni.plantMe.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
@Entity
@Table(name = "current_weather")
public class CurrentWeather extends BaseEntity{
    @Column(nullable = false)
    private LocalDateTime time;
    @Column(nullable = false)
    private double temperature;
    @Column(nullable = false)
    private double rain;
    @Column(nullable = false, name = "wind_speed")
    private double windSpeed;

    public CurrentWeather () {}

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
