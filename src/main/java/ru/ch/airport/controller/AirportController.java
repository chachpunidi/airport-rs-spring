package ru.ch.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import ru.ch.airport.dto.AircraftDto;
import ru.ch.airport.dto.AirportDto;
import ru.ch.airport.service.aircraft.AircraftService;
import ru.ch.airport.service.airport.AirportService;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<AirportDto> airports() {
        return airportService.findAirports();
    }

    @GetMapping("/{code}")
    public AirportDto airport(@PathVariable(value = "code") String code) {
        return airportService.findAirport(code);
    }

    @PutMapping
    public Integer createAirport(@RequestBody AirportDto airport) {
        return airportService.createAirport(airport);
    }
    @PostMapping("/batch")
    public Integer createAirports(@RequestBody List<AirportDto> airports) {
        return airportService.createAirports(airports);
    }
    @DeleteMapping("/{code}")
    public Integer deleteAirport(@PathVariable(value = "code") String code) {
        return airportService.deleteAirport(code);
    }
    @PatchMapping("/{code}")
    public Integer updateAirport(@PathVariable(value = "code") String code, @RequestBody AirportDto airport) {
        return airportService.updateAirport(code, airport);
    }

}