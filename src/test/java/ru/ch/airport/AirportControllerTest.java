package ru.ch.airport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ch.airport.controller.AircraftController;
import ru.ch.airport.controller.AirportController;
import ru.ch.airport.dto.AircraftDto;
import ru.ch.airport.dto.AirportDto;
import ru.ch.airport.service.aircraft.AircraftService;

@SpringBootTest
public class AirportControllerTest {


    @Autowired
    private AirportController airportController;

    @Autowired
    private AircraftController aircraftController;

    @Autowired
    private AircraftService aircraftService;

    @Test
    public void testAirport() {
        AirportDto airportDtoVar = airportController.airports().get(0);
        Assertions.assertNotNull(airportDtoVar.getCode());
    }
    @Test
    public void testAircraft(){
        String code = "319";
        AircraftDto aircraftDtoVar = aircraftController.aircraft(code);
        Assertions.assertNotNull(aircraftDtoVar);
        Assertions.assertEquals(code, aircraftDtoVar.getCode());
        Assertions.assertEquals("Airbus A319-100", aircraftDtoVar.getModel());
    }

    @Test
    public void getAirportByCode() {
        String code = "MJZ";
        AirportDto airportDtoVar = airportController.airport(code);
        Assertions.assertNotNull(airportDtoVar);
        Assertions.assertEquals(code, airportDtoVar.getCode());
        Assertions.assertEquals("Мирный", airportDtoVar.getName());
    }

    @Test
    public void getAirportByCode2() {
        String code = "OGZ";
        AirportDto airportDtoVar = airportController.airport(code);
        Assertions.assertNotNull(airportDtoVar);
        Assertions.assertEquals(code, airportDtoVar.getCode());
        Assertions.assertEquals("Беслан", airportDtoVar.getName());
    }

    @Test
    public void getAirportEmpty() {
        String code = "MJZfdfdfdf";
        AirportDto airportDtoVar = airportController.airport(code);
        Assertions.assertNull(airportDtoVar);

    }

}
