package ru.ch.airport.dto;

public class AirportDto {
    private String name;
    private String code;
    private String city;
    private Float longitude;
    private Float latitude;

    private String timezone;

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLatitude() {
        return latitude;
    }


    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLongitude() {
        return longitude;
    }


    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
