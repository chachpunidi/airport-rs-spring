package ru.ch.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ch.airport.dto.AircraftDto;
import ru.ch.airport.service.aircraft.AircraftService;

import java.util.List;

@RestController
@RequestMapping("/aircrafts")
public class AircraftController {

    private AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }


    @GetMapping
    public List<AircraftDto> aircrafts() {
        return aircraftService.findAircrafts();
    }

    @GetMapping("/{code}")
    public AircraftDto aircraft(@PathVariable(value = "code") String code) {
        return aircraftService.findAircraft(code);
    }

    @PutMapping
    public Integer createAircraft(@RequestBody AircraftDto aircraft) {
      return   aircraftService.createAircraft(aircraft);
    }

    @PutMapping("batch")
    public Integer createAircrafts(@RequestBody List<AircraftDto> aircrafts) {
      return   aircraftService.createAircrafts(aircrafts);
    }

    @PatchMapping("/{code}")
    public Integer updateAircraft(@PathVariable(value = "code") String code, @RequestBody AircraftDto aircraft) {
        return  aircraftService.updateAircraft(code, aircraft);
    }

    @DeleteMapping("/{code}")
    public Integer deleteAircraft(@PathVariable(value = "code") String code) {
        return aircraftService.deleteAircraft(code);
    }
}



