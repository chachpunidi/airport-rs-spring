package ru.ch.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ch.airport.dto.AircraftDto;
import ru.ch.airport.dto.AirportDto;
import ru.ch.airport.service.aircraft.AircraftService;
import ru.ch.airport.service.airport.AirportService;

import java.util.List;

@RestController
public class AirportController {

    private AirportService airportService;
    private AircraftService aircraftService;

    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
    public AirportController(AirportService airportService, AircraftService aircraftService) {
        this.airportService = airportService;
        this.aircraftService = aircraftService;
    }

    @GetMapping("/airports")
    public List<AirportDto> airports() {
        return airportService.airports();
    }

    @GetMapping("/aircraft")
    public AircraftDto aircraft() {
        return aircraftService.aircraft();
    }

    public AircraftDto aircraft1() {
        return aircraftService.aircraft1();
    }

    public AircraftDto aircraft2() {
        return aircraftService.aircraft2(); //rt
    }
}
