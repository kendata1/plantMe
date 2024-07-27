package bg.softuni.plantMe.models.DTOs;

import com.google.gson.annotations.SerializedName;

public class CurrentWeatherDTO {
    private String time;
    @SerializedName("temperature_2m")
    private double temperature;
    @SerializedName("is_day")
    private int isDay;
    @SerializedName("cloud_cover")
    private double cloudCover;
    private double rain;
    @SerializedName("wind_speed_10m")
    private double windSpeed;
    private int interval;
    private String imageUrl;

    public CurrentWeatherDTO () {}

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getIsDay() {
        return isDay;
    }

    public void setIsDay(int isDay) {
        this.isDay = isDay;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

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


    @Override
    public String toString() {
        return "CurrentWeatherDTO{" +
                "time='" + time + '\'' +
                ", temperature=" + temperature +
                ", isDay=" + isDay +
                ", cloudCover=" + cloudCover +
                ", rain=" + rain +
                ", windSpeed=" + windSpeed +
                ", interval=" + interval +
                '}';
    }
}
