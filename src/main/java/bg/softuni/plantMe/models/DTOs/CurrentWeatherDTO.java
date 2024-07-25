package bg.softuni.plantMe.models.DTOs;

import java.time.LocalDateTime;

public class CurrentWeatherDTO {
    private String time;
    private int interval;
    private double temperature_2m;
    private double rain;
    private double wind_speed_10m;

    public CurrentWeatherDTO () {}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }


    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(double temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public double getWind_speed_10m() {
        return wind_speed_10m;
    }

    public void setWind_speed_10m(double wind_speed_10m) {
        this.wind_speed_10m = wind_speed_10m;
    }

    @Override
    public String toString() {
        return "CurrentWeatherDTO{" +
                "time=" + time +
                ", interval=" + interval +
                ", temperature_2m=" + temperature_2m +
                ", rain=" + rain +
                ", wind_speed_10m=" + wind_speed_10m +
                '}';
    }
}
