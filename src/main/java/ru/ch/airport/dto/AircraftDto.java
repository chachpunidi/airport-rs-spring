package ru.ch.airport.dto;


public class AircraftDto {

    private String code;
    private String manufacturer;
    private String model;
    private Long range;


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


    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "AircraftDto{" +
                "code='" + code + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", range=" + range +
                '}';
    }
}
