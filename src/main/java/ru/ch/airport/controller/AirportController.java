package ru.ch.airport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ch.airport.dto.AircraftDto;
import ru.ch.airport.dto.AirportDto;
import ru.ch.airport.service.aircraft.AircraftService;
import ru.ch.airport.service.aircraft.AircraftServiceIml;
import ru.ch.airport.service.airport.AirportService;
import ru.ch.airport.service.airport.AirportServiceImpl;

@RestController
public class AirportController {
    private AirportService airportService = new AirportServiceImpl();
    private AircraftService aircraftService = new AircraftServiceIml();

    @GetMapping("/airports")
    public AirportDto airports() {

        return airportService.airport();
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
