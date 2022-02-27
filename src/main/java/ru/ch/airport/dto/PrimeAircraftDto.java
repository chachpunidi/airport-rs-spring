package ru.ch.airport.dto;

public class PrimeAircraftDto {
    private String code;
    private String manufacturer;
    private String model;
    private long places;


    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getPlaces() {return places;


    }


    public void setPlaces(long places) {
        this.places = places;
    }
}
